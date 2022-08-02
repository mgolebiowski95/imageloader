package com.examples.imageloaderlibrary.task;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.examples.imageloaderlibrary.Build;
import com.examples.imageloaderlibrary.cache.DiskCache;
import com.examples.imageloaderlibrary.cache.MemoryCache;
import com.examples.imageloaderlibrary.decoder.ImageDecoder;
import com.examples.imageloaderlibrary.imagesource.ImageSource;
import com.examples.imageloaderlibrary.listener.ImageLoadingListener;
import com.examples.imageloaderlibrary.logger.Logcat;
import com.examples.imageloaderlibrary.process.BitmapTransformation;
import com.examples.imageloaderlibrary.util.ImageSize;
import com.examples.imageloaderlibrary.util.KeyTool;

import java.lang.ref.WeakReference;

/**
 * Created by Mariusz
 */
public class LoadImageTask extends AsyncTask<Void, Void, LoadImageTask.Result> {
    private final ImageSource source;
    private final ImageLoadingOptions options;
    private final ImageSize targetSize;
    private final MemoryCache memoryCache;
    private final DiskCache diskCache;
    private final ImageDecoder decoder;
    private BitmapTransformation postTransformation;
    private ImageLoadingListener listener;
    private WeakReference<ImageView> imageViewReference;

    public LoadImageTask(ImageSource source, ImageLoadingOptions options, MemoryCache memoryCache, DiskCache diskCache, ImageDecoder decoder, ImageLoadingListener listener) {
        this.source = source;
        this.options = options;
        this.targetSize = options.getTargetSize();
        this.memoryCache = memoryCache;
        this.diskCache = diskCache;
        this.decoder = decoder;
        this.postTransformation = options.getPostTransformation();
        this.listener = listener;
    }

    public LoadImageTask(ImageSource source, ImageLoadingOptions options, MemoryCache memoryCache, DiskCache diskCache, ImageDecoder decoder, ImageView imageView) {
        this.source = source;
        this.options = options;
        this.targetSize = options.getTargetSize();
        this.memoryCache = memoryCache;
        this.diskCache = diskCache;
        this.decoder = decoder;
        this.postTransformation = options.getPostTransformation();
        this.imageViewReference = new WeakReference<>(imageView);
    }

    @Override
    protected void onPreExecute() {
        if (listener != null)
            listener.onLoadingStarted();
    }

    @Override
    protected LoadImageTask.Result doInBackground(Void... voids) {
        if (Build.DEBUG)
            Logcat.DEBUG.log(this, "doInBackground - starting work");

        Bitmap decodedBitmap = null;
        try {
            String key = source.toString();
            if (!options.isSkipMemoryCache() && memoryCache != null) {
                decodedBitmap = memoryCache.get(key);
                if (decodedBitmap != null) {
                    if (Build.DEBUG)
                        Logcat.DEBUG.log(this, "doInBackground - finished work");
                    return new Result(decodedBitmap);
                }
            }
            if (!options.isSkipDiskCache() && diskCache != null) {
                decodedBitmap = diskCache.get(KeyTool.getCacheKey(key), options.getConfig());
                if (decodedBitmap != null) {
                    if (!options.isSkipMemoryCache() && memoryCache != null)
                        memoryCache.put(key, decodedBitmap);
                    if (Build.DEBUG)
                        Logcat.DEBUG.log(this, "doInBackground - finished work");
                    return new Result(decodedBitmap);
                }
            }

            decodedBitmap = decoder.decode(source, targetSize, options.getConfig());
            if (postTransformation != null)
                decodedBitmap = postTransformation.transform(decodedBitmap);
            if (decodedBitmap != null) {
                if (!options.isSkipMemoryCache() && memoryCache != null)
                    memoryCache.put(key, decodedBitmap);
                if (!options.isSkipDiskCache() && diskCache != null)
                    diskCache.save(KeyTool.getCacheKey(key), decodedBitmap);
            }
        } catch (Throwable e) {
            if (decodedBitmap != null) {
                if (!decodedBitmap.isRecycled())
                    decodedBitmap.recycle();
            }
            return new Result(e);
        }
        if (Build.DEBUG)
            Logcat.DEBUG.log(this, "doInBackground - finished work");
        return new Result(decodedBitmap);
    }

    @Override
    protected void onPostExecute(LoadImageTask.Result result) {
        if (listener != null) {
            if (result.isError())
                listener.onLoadingFailed(result.getThrowable());
            else
                listener.onLoadingComplete(result.getBitmap());
        }

        final ImageView imageView = getAttachedImageView();
        if (imageView != null) {
            if (options.isAnimateFadeIn()) {
                Drawable placeholder;
                if (options.getPlaceHolder() != null)
                    placeholder = new BitmapDrawable(imageView.getResources(), options.getPlaceHolder());
                else
                    placeholder = new ColorDrawable(Color.parseColor("#ffFFFFFF"));
                final TransitionDrawable transition = new TransitionDrawable(new Drawable[]{
                        placeholder, new BitmapDrawable(imageView.getResources(), result.getBitmap())
                });
                imageView.setImageDrawable(transition);
                transition.startTransition(150);
            } else {
                imageView.setImageBitmap(result.getBitmap());
            }
        }
    }

    public class Result {
        private Bitmap bitmap;
        private Throwable throwable;

        public Result(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public Result(Throwable t) {
            this.throwable = t;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public boolean isError() {
            return throwable != null;
        }
    }

    private ImageView getAttachedImageView() {
        if (imageViewReference != null) {
            final ImageView imageView = imageViewReference.get();
            final LoadImageTask loadImageTask = getImageLoadingTask(imageView);

            if (this == loadImageTask)
                return imageView;
        }
        return null;
    }

    public static LoadImageTask getImageLoadingTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();

            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getImageLoadingTask();
            }
        }
        return null;
    }

    public static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<LoadImageTask> imageLoadingTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap, LoadImageTask bitmapWorkerTask) {
            super(res, bitmap);
            imageLoadingTaskReference = new WeakReference<>(bitmapWorkerTask);
        }

        public LoadImageTask getImageLoadingTask() {
            return imageLoadingTaskReference.get();
        }
    }

    public ImageSource getSource() {
        return source;
    }
}
