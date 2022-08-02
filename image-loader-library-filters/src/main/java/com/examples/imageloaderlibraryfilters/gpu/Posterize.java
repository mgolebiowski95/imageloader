package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePosterizeFilter;

/**
 * Created by Mariusz
 */
public class Posterize extends GPUImageBitmapTransformation {
    private int colorLevels = 1;

    public Posterize(Context context, int colorLevels) {
        super(context);
        this.colorLevels = colorLevels;
    }

    @Override
    protected GPUImageFilter onCreateGPUImageFilter() {
        return new GPUImagePosterizeFilter(colorLevels);
    }
}
