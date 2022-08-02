package com.examples.imageloaderlibraryfilters.android;

import android.graphics.ColorMatrix;

/**
 * Created by Mariusz
 */
public class Sepia extends ColorMatrixColorFilterWithoutAlpha {
    @Override
    protected ColorMatrix getColorMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrix colorScale = new ColorMatrix();
        colorScale.setScale(1, 1, 0.8f, 1);

        // Convert to grayScale, then apply brown color
        colorMatrix.postConcat(colorScale);
        return colorMatrix;
    }
}
