package com.examples.imageloaderlibraryfilters.develop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.examples.imageloaderlibrary.process.BitmapTransformation;

/**
 * Created by Mariusz
 */
public class PorterDuffFilterWithAlpha implements BitmapTransformation {
    private final Bitmap mask;
    private final PorterDuff.Mode mode;

    public PorterDuffFilterWithAlpha(Bitmap mask, PorterDuff.Mode mode) {
        this.mask = mask;
        this.mode = mode;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        Bitmap canvasBitmap = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(toTransform, 0, 0, null);
        Paint maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(mode));
        canvas.drawBitmap(mask, 0, 0, maskPaint);
        return canvasBitmap;
    }
}
