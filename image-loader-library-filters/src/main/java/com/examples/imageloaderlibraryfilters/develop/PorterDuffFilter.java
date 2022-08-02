package com.examples.imageloaderlibraryfilters.develop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.examples.imageloaderlibrary.decoder.ImageDecoder;
import com.examples.imageloaderlibrary.decoder.ImageDecoderImpl;
import com.examples.imageloaderlibrary.imagesource.ImageSource;
import com.examples.imageloaderlibrary.process.BitmapTransformation;
import com.examples.imageloaderlibrary.util.ImageSize;

import java.io.IOException;

/**
 * Created by Mariusz
 */
public class PorterDuffFilter implements BitmapTransformation {
    private final Context context;
    private final ImageSource source;
    private final PorterDuff.Mode mode;

    public PorterDuffFilter(Context context, ImageSource source, PorterDuff.Mode mode) {
        this.context = context;
        this.source = source;
        this.mode = mode;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        Canvas canvas = new Canvas(toTransform);
        canvas.drawBitmap(toTransform, 0, 0, null);
        Paint maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(mode));
        Bitmap mask = decodeResource();
        canvas.drawBitmap(mask, 0, 0, maskPaint);
        return toTransform;
    }

    private Bitmap decodeResource() {
        ImageDecoder decoder = new ImageDecoderImpl();
        try {
            return decoder.decode(source, ImageSize.ORIGINAL, Bitmap.Config.ARGB_8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
