package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import com.examples.imageloaderlibrary.imagesource.ImageSource;

import jp.co.cyberagent.android.gpuimage.GPUImageLightenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;

/**
 * Created by Mariusz
 */
public class Lighten extends GPUImageTwoInputBitmapTransformation {
    public Lighten(Context context, ImageSource source) {
        super(context, source);
    }

    @Override
    protected GPUImageTwoInputFilter onCreateGPUImageFilter() {
        return new GPUImageLightenBlendFilter();
    }
}
