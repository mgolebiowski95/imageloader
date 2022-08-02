package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;

import com.examples.imageloaderlibrary.imagesource.ImageSource;

import jp.co.cyberagent.android.gpuimage.GPUImageDissolveBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;

/**
 * Created by Mariusz
 *
 * Mix ranges from 0.0 (only image 1) to 1.0 (only image 2), with 0.5 (half of either) as the normal level
 */
public class Dissolve extends GPUImageTwoInputBitmapTransformation {
    private final float mix;

    public Dissolve(Context context, ImageSource source) {
        super(context, source);
        this.mix = 0.5f;
    }

    public Dissolve(Context context, ImageSource source, float mix) {
        super(context, source);
        this.mix = mix;
    }

    @Override
    protected GPUImageTwoInputFilter onCreateGPUImageFilter() {
        return new GPUImageDissolveBlendFilter(mix);
    }
}
