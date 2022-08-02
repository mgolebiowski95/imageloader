package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;

/**
 * Created by Mariusz

 * Changes the contrast of the image.<br>
 * <br>
 * contrast value ranges from 0.0 to 4.0, with 1.0 as the normal level
 */
public class Contrast extends GPUImageBitmapTransformation<GPUImageContrastFilter> {
    private final int contrast;

    public Contrast(Context context, int contrast) {
        super(context);
        this.contrast = contrast;
    }

    @Override
    protected GPUImageContrastFilter onCreateGPUImageFilter() {
        return new GPUImageContrastFilter();
    }

    @Override
    protected void onGPUImageFilterCreated(GPUImageContrastFilter filter) {
        filter.setContrast(contrast);
    }
}
