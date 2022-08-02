package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import com.examples.imageloaderlibrary.imagesource.ImageSource;

import jp.co.cyberagent.android.gpuimage.GPUImageHardLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;

/**
 * Created by Mariusz
 */
public class HardLight extends GPUImageTwoInputBitmapTransformation {
    public HardLight(Context context, ImageSource source) {
        super(context, source);
    }

    @Override
    protected GPUImageTwoInputFilter onCreateGPUImageFilter() {
        return new GPUImageHardLightBlendFilter();
    }
}
