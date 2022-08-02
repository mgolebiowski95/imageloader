package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;
import android.graphics.Bitmap;

import com.examples.imageloaderlibrary.process.BitmapTransformation;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/**
 * Created by Mariusz
 */
public abstract class GPUImageBitmapTransformation<T extends GPUImageFilter> implements BitmapTransformation {
    protected final Context context;

    public GPUImageBitmapTransformation(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        GPUImage gpuImage = new GPUImage(context);
        gpuImage.setImage(toTransform);
        T filter = onCreateGPUImageFilter();
        onGPUImageFilterCreated(filter);
        gpuImage.setFilter(filter);
        return gpuImage.getBitmapWithFilterApplied();
    }

    protected abstract T onCreateGPUImageFilter();

    protected void onGPUImageFilterCreated(T filter) {}
}
