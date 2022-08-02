package com.examples.imageloaderlibrary.decoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.examples.imageloaderlibrary.imagesource.BitmapImageSource;
import com.examples.imageloaderlibrary.imagesource.ImageSource;
import com.examples.imageloaderlibrary.imagesource.ResourceImageSource;
import com.examples.imageloaderlibrary.util.ImageSize;
import com.examples.imageloaderlibrary.util.ImageSizeUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class ImageDecoderImpl implements ImageDecoder {

    @Override
    public Bitmap decode(ImageSource source, ImageSize targetSize, Bitmap.Config config) throws IOException {
        Bitmap bitmap;
        if (source instanceof BitmapImageSource) {
            Bitmap src = ((BitmapImageSource) source).getBitmap();
            float xScale = (float) targetSize.getWidth() / src.getWidth();
            float yScale = (float) targetSize.getHeight() / src.getHeight();
            float scale = Math.max(xScale, yScale);
            bitmap = Bitmap.createScaledBitmap(src, (int) (src.getWidth() * scale), (int) (src.getHeight() * scale), true);
        } else if (source instanceof ResourceImageSource) {
            Context context = ((ResourceImageSource) source).getContext();
            int resourceId = ((ResourceImageSource) source).getResourceId();
            Drawable drawable = ContextCompat.getDrawable(context, resourceId);
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } else {
            ImageSize srcSize = measureImage(source.getStream());
            BitmapFactory.Options options = prepareDecodingOptions(srcSize, targetSize, config);
            bitmap = BitmapFactory.decodeStream(source.getStream(), null, options);
        }
        return bitmap;
    }

    private BitmapFactory.Options prepareDecodingOptions(ImageSize srcSize, ImageSize targetSize, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        options.inPreferredConfig = config;
        options.inSampleSize = ImageSizeUtils.computeImageSampleSize(srcSize, targetSize);
        return options;
    }

    private ImageSize measureImage(InputStream is) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);
        if (is.markSupported())
            is.reset();
        return new ImageSize(options.outWidth, options.outHeight);
    }
}
