package com.examples.imageloaderlibrary.imagesource;

import android.graphics.Bitmap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class BitmapImageSource implements ImageSource {
    private final Bitmap bitmap;
    private final String key;

    public BitmapImageSource(Bitmap bitmap, String key) {
        this.bitmap = bitmap;
        this.key = key;
    }

    @Override
    public InputStream getStream() throws IOException {
        return null;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public String toString() {
        return key;
    }
}
