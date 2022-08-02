package com.examples.imageloaderlibrary;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.examples.imageloaderlibrary.cache.DiskCache;
import com.examples.imageloaderlibrary.cache.DiskCacheImpl;
import com.examples.imageloaderlibrary.cache.MemoryCache;
import com.examples.imageloaderlibrary.cache.MemoryCacheImpl;
import com.examples.imageloaderlibrary.decoder.ImageDecoder;
import com.examples.imageloaderlibrary.decoder.ImageDecoderImpl;
import com.examples.imageloaderlibrary.imagesource.ImageSource;
import com.examples.imageloaderlibrary.listener.ImageLoadingListener;
import com.examples.imageloaderlibrary.logger.Logcat;
import com.examples.imageloaderlibrary.task.ImageLoadingOptions;
import com.examples.imageloaderlibrary.task.LoadImageTask;
import com.examples.imageloaderlibrary.util.KeyTool;

/**
 * Created by Mariusz
 * <p>
 * https://github.com/nostra13/Android-Universal-Image-Loader
 * https://github.com/patrickfav/Dali
 * https://github.com/square/picasso
 * https://github.com/googlesamples/android-DisplayingBitmaps
 * https://stuff.mit.edu/afs/sipb/project/android/docs/training/displaying-bitmaps/index.html
 * https://github.com/bumptech/glide
 * moja stara bibliotega do ladowania obrazow
 */
public class ImageLoader {
    private static ImageLoader instance;

    private MemoryCache memoryCache;
    private DiskCache diskCache;
    private ImageDecoder decoder;

    private final KeyTool keyTool = new KeyTool();

    public static ImageLoader getInstance() {
        if (instance == null)
            instance = new ImageLoader();
        return instance;
    }

    public ImageLoader() {
        decoder = new ImageDecoderImpl();
        memoryCache = new MemoryCacheImpl();
    }

    public void init(Context context) {
        diskCache = new DiskCacheImpl(context);
    }

    public LoadImageTask load(ImageSource source, ImageLoadingOptions options, ImageLoadingListener listener) {
        return new LoadImageTask(source, options, memoryCache, diskCache, decoder, listener);
    }

    public void display(ImageSource source, ImageLoadingOptions options, final ImageView imageView, Resources resources) {
        keyTool.setSource(source);
        keyTool.setOptions(options);
        if (cancelPotentialWork(keyTool.generateKey(), imageView)) {
            LoadImageTask loadImageTask = new LoadImageTask(source, options, memoryCache, diskCache, decoder, imageView);
            LoadImageTask.AsyncDrawable asyncDrawable = new LoadImageTask.AsyncDrawable(resources, options.getPlaceHolder(), loadImageTask);
            imageView.setImageDrawable(asyncDrawable);
            loadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private boolean cancelPotentialWork(String nextTaskKey, ImageView imageView) {
        final LoadImageTask loadImageTask = LoadImageTask.getImageLoadingTask(imageView);

        if (loadImageTask != null) {
            final String currentTaskKey = loadImageTask.getSource().toString();
            if (currentTaskKey == null || !currentTaskKey.equals(nextTaskKey)) {
                loadImageTask.cancel(true);
                if (Build.DEBUG)
                    Logcat.DEBUG.log(this, "cancelPotentialWork - cancelled work for " + currentTaskKey);
            } else {
                // The same work is already in progress.
                return false;
            }
        }
        return true;
    }

    public void clearMemoryCache() {
        if (memoryCache != null)
            memoryCache.clear();
    }

    public void clearDiskCache() {
        if (diskCache != null)
            diskCache.clear();
    }
}
