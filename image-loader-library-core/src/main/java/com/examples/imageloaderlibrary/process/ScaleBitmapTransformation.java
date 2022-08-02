package com.examples.imageloaderlibrary.process;

import android.graphics.Bitmap;

import com.examples.imageloaderlibrary.util.ImageSize;

/**
 * Created by Mariusz
 */
public class ScaleBitmapTransformation implements BitmapTransformation {
    private final ImageSize targetSize;

    public ScaleBitmapTransformation(ImageSize targetSize) {
        this.targetSize = targetSize;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        if (toTransform.getWidth() != targetSize.getWidth() || toTransform.getHeight() != targetSize.getHeight())
            return Bitmap.createScaledBitmap(toTransform, targetSize.getWidth(), targetSize.getHeight(), true);
        return toTransform;
    }
}
