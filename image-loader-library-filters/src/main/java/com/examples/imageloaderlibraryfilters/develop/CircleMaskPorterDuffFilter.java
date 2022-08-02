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
public class CircleMaskPorterDuffFilter implements BitmapTransformation {
    @Override
    public Bitmap transform(Bitmap toTransform) {
        Bitmap canvasBitmap = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(toTransform, 0, 0, null);
        Bitmap mask = createCircleMask(toTransform);

        Paint maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mask, 0, 0, maskPaint);
        return canvasBitmap;
    }

    Bitmap createCircleMask(Bitmap bitmap) {
        int minor = Math.min(bitmap.getWidth(), bitmap.getHeight());
        int major = Math.max(bitmap.getWidth(), bitmap.getHeight());
        Bitmap mask = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mask);
        canvas.drawCircle(minor / 2f, major / 2f, minor / 2, new Paint(Paint.ANTI_ALIAS_FLAG));
        return mask;
    }
}
