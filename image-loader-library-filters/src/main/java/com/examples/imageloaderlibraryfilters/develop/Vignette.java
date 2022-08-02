package com.examples.imageloaderlibraryfilters.develop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import com.examples.imageloaderlibrary.process.BitmapTransformation;

/**
 * Created by Mariusz
 */
public class Vignette implements BitmapTransformation {
    float offsetFactor = 0.35f;

    public Vignette() {
    }

    public Vignette(float offsetFactor) {
        this.offsetFactor = offsetFactor;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        Canvas canvas = new Canvas(toTransform);
        Paint paint = new Paint();
        canvas.drawBitmap(toTransform, 0, 0, paint);
        canvas.drawBitmap(generate(toTransform.getWidth(), toTransform.getHeight()), 0, 0, null);
        return toTransform;
    }

    private Paint generateVignette(float width, float height) {
        Paint fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float centerX = width / 2;
        float centerY = height / 2;
        float radius = Math.min(width, height) / 2f;
        System.out.println(centerX + " " + centerY + " " + radius);
        fillPaint.setShader(new RadialGradient(centerX,
                                               centerY,
                                               radius + (offsetFactor * Math.min(width, height)),
                                               new int[]{Color.BLACK, Color.TRANSPARENT},
                                               new float[]{0, 1},
                                               Shader.TileMode.CLAMP));
        fillPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        return fillPaint;
    }

    private Bitmap generate(float width, float height) {
        Bitmap bitmap = Bitmap.createBitmap((int)width, (int)height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(0, 0, width, height, generateVignette(width, height));
        return bitmap;
    }
}
