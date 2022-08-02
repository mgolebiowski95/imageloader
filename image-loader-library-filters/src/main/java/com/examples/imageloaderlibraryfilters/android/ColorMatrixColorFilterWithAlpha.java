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
public abstract class ColorMatrixColorFilterWithAlpha implements BitmapTransformation {
    @Override
    public Bitmap transform(Bitmap toTransform) {
        Bitmap canvasBitmap = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix()));
        canvas.drawBitmap(toTransform, 0, 0, paint);
        return canvasBitmap;
    }

    protected abstract ColorMatrix getColorMatrix();
}

