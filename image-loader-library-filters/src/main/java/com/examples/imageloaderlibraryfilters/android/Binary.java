package com.examples.imageloaderlibraryfilters.android;

import android.graphics.ColorMatrix;

/**
 * Created by Mariusz
 */
public class Binary extends ColorMatrixColorFilterWithoutAlpha {
    @Override
    protected ColorMatrix getColorMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        float m = 255f;
        float t = -255 * 128f;
        ColorMatrix threshold = new ColorMatrix(new float[]{
                m, 0, 0, 1, t,
                0, m, 0, 1, t,
                0, 0, m, 1, t,
                0, 0, 0, 1, 0
        });

        // Convert to grayScale, then scale and clamp
        colorMatrix.postConcat(threshold);
        return colorMatrix;
    }
}
