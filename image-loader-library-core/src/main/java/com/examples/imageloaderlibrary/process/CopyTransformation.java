package com.examples.imageloaderlibrary.process;

import android.graphics.Bitmap;

/**
 * Created by Mariusz
 */
public class CopyTransformation implements BitmapTransformation {
    private final Bitmap.Config config;
    private final boolean mutable;

    public CopyTransformation(Bitmap.Config config, boolean mutable) {
        this.config = config;
        this.mutable = mutable;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        return toTransform.copy(config, mutable);
    }
}
