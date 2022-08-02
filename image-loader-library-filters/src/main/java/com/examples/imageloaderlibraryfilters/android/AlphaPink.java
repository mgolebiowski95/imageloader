package com.examples.imageloaderlibraryfilters.android;

import android.graphics.ColorMatrix;

/**
 * Created by Mariusz
 */
public class AlphaPink extends ColorMatrixColorFilterWithAlpha {
    @Override
    protected ColorMatrix getColorMatrix() {
        return new ColorMatrix(new float[]{
                0, 0, 0, 0, 255,
                0, 0, 0, 0, 0,
                0.2f, 0, 0, 0, 50,
                0.2f, 0.2f, 0.2f, 0, -20
        });
    }
}
