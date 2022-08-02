package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;

/**
 * Created by Mariusz
 */
public class Sketch extends GPUImageBitmapTransformation {
    public Sketch(Context context) {
        super(context);
    }

    @Override
    protected GPUImageFilter onCreateGPUImageFilter() {
        return new GPUImageSketchFilter();
    }
}
