package com.examples.imageloaderlibraryfilters.android;

import android.graphics.ColorMatrix;

/**
 * Created by Mariusz
 */
public class ContrastBrightness extends ColorMatrixColorFilterWithoutAlpha {
    private final float contrast;
    private final float brightness;

    /**
     * @param contrast   0..10 1 is default
     * @param brightness -255..255 0 is default
     */
    public ContrastBrightness(float contrast, float brightness) {
        this.contrast = contrast;
        this.brightness = brightness;
    }

    @Override
    protected ColorMatrix getColorMatrix() {
        return new ColorMatrix(new float[]{
                contrast, 0, 0, 0, brightness,
                0, contrast, 0, 0, brightness,
                0, 0, contrast, 0, brightness,
                0, 0, 0, 1, 0
        });
    }
}
