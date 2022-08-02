package com.examples.imageloaderlibraryfilters.android;

import android.graphics.ColorMatrix;

/**
 * Created by Mariusz
 */
public class GrayScale extends ColorMatrixColorFilterWithoutAlpha {
    @Override
    protected ColorMatrix getColorMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        return colorMatrix;
    }
}
