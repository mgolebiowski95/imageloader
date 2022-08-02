package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import com.examples.imageloaderlibrary.imagesource.ImageSource;

import jp.co.cyberagent.android.gpuimage.GPUImageScreenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;

/**
 * Created by Mariusz
 */
public class Screen extends GPUImageTwoInputBitmapTransformation {
    public Screen(Context context, ImageSource source) {
        super(context, source);
    }

    @Override
    protected GPUImageTwoInputFilter onCreateGPUImageFilter() {
        return new GPUImageScreenBlendFilter();
    }
}
