package com.examples.imageloaderlibrary.process;

import android.graphics.Bitmap;

import java.io.OutputStream;

/**
 * Created by Mariusz
 */
public class SaveImageTransformation implements BitmapTransformation {
    private final OutputStream outputStream;

    public SaveImageTransformation(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        if (toTransform != null) {
            toTransform.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            if (!toTransform.isRecycled())
                toTransform.recycle();
        }
        return null;
    }
}
