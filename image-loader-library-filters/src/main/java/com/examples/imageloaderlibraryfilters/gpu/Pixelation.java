package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;

/**
 * Created by Mariusz
 */
public class Pixelation extends GPUImageBitmapTransformation {
    public Pixelation(Context context) {
        super(context);
    }

    @Override
    protected GPUImageFilter onCreateGPUImageFilter() {
        return new GPUImagePixelationFilter();
    }
}
