package com.examples.imageloaderlibrary.process;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Mariusz
 */
public class PreDrawColor implements BitmapTransformation {
    private final int color;

    public PreDrawColor(int color) {
        this.color = color;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        Bitmap canvasBitmap = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawColor(color);
        canvas.drawBitmap(toTransform, 0, 0, null);
        return canvasBitmap;
    }
}
