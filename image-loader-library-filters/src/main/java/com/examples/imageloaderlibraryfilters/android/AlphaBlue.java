package com.examples.imageloaderlibraryfilters.android;

import android.graphics.ColorMatrix;

/**
 * Created by Mariusz
 */
public class AlphaBlue extends ColorMatrixColorFilterWithAlpha {
    @Override
    protected ColorMatrix getColorMatrix() {
        return new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,
                0.3f, 0, 0, 0, 50,
                0, 0, 0, 0, 255,
                0.2f, 0.4f, 0.4f, 0, -30
        });
    }
}
