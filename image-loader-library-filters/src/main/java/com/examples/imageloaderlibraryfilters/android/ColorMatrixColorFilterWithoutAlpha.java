package com.examples.imageloaderlibraryfilters.android;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.examples.imageloaderlibrary.process.BitmapTransformation;

/**
 * Created by Mariusz
 */
public abstract class ColorMatrixColorFilterWithoutAlpha implements BitmapTransformation {
    @Override
    public Bitmap transform(Bitmap toTransform) {
        Canvas canvas = new Canvas(toTransform);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix()));
        canvas.drawBitmap(toTransform, 0, 0, paint);
        return toTransform;
    }

    protected abstract ColorMatrix getColorMatrix();
}
