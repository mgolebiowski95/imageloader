package com.examples.imageloaderlibrary.process;

import android.graphics.Bitmap;

/**
 * Created by Mariusz
 */
public class None implements BitmapTransformation {
    @Override
    public Bitmap transform(Bitmap toTransform) {
        return toTransform;
    }
}
