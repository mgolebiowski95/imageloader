package com.examples.imageloaderlibrary.task;

import android.graphics.Bitmap;

import com.examples.imageloaderlibrary.process.BitmapTransformation;
import com.examples.imageloaderlibrary.util.ImageSize;

/**
 * Created by Mariusz
 */
public class ImageLoadingOptions {
    private Bitmap placeHolder;
    private boolean skipMemoryCache;
    private boolean skipDiskCache;
    private ImageSize targetSize;
    private Bitmap.Config config;
    private BitmapTransformation postTransformation;
    private boolean animateFadeIn;

    private ImageLoadingOptions(Builder builder) {
        placeHolder = builder.placeHolder;
        skipMemoryCache = builder.skipMemoryCache;
        skipDiskCache = builder.skipDiskCache;
        targetSize = builder.targetSize;
        config = builder.config;
        postTransformation = builder.postProcessor;
        animateFadeIn = builder.animateFadeIn;
    }

    public Bitmap getPlaceHolder() {
        return placeHolder;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public boolean isSkipDiskCache() {
        return skipDiskCache;
    }

    public ImageSize getTargetSize() {
        return targetSize;
    }

    public Bitmap.Config getConfig() {
        return config;
    }

    public BitmapTransformation getPostTransformation() {
        return postTransformation;
    }

    public boolean isAnimateFadeIn() {
        return animateFadeIn;
    }

    @Override
    public String toString() {
        return "ImageLoadingOptions{" +
                ", targetSize=" + targetSize +
                ", config=" + config +
                ", postTransform=" + postTransformation +
                '}';
    }

    public static final class Builder {
        private Bitmap placeHolder;
        private boolean skipMemoryCache;
        private boolean skipDiskCache;
        private ImageSize targetSize;
        private Bitmap.Config config;
        private BitmapTransformation postProcessor;
        private boolean animateFadeIn;

        public Builder() {
        }

        public Builder placeHolder(Bitmap placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder skipMemoryCache() {
            skipMemoryCache = true;
            return this;
        }

        public Builder skipDiskCache() {
            skipDiskCache = true;
            return this;
        }

        public Builder targetSize(ImageSize val) {
            targetSize = val;
            return this;
        }

        public Builder config(Bitmap.Config val) {
            config = val;
            return this;
        }

        public Builder postTransform(BitmapTransformation val) {
            postProcessor = val;
            return this;
        }

        public Builder fadeIn(boolean val) {
            animateFadeIn = val;
            return this;
        }

        public ImageLoadingOptions build() {
            if(targetSize == null)
                targetSize = ImageSize.ORIGINAL;
            if(config == null)
                config = Bitmap.Config.ARGB_8888;
            return new ImageLoadingOptions(this);
        }
    }
}
