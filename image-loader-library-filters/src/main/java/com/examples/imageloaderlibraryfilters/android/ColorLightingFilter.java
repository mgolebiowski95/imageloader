package com.examples.imageloaderlibraryfilters.android;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

import com.examples.imageloaderlibrary.process.BitmapTransformation;

/**
 * Created by Mariusz
 */
public class ColorLightingFilter implements BitmapTransformation {
    private final int mul;
    private final int add;

    public ColorLightingFilter(int mul, int add) {
        this.mul = mul;
        this.add = add;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        Canvas canvas = new Canvas(toTransform);
        Paint paint = new Paint();
        paint.setColorFilter(new LightingColorFilter(mul, add));
        canvas.drawBitmap(toTransform, 0, 0, paint);
        return toTransform;
    }
}
