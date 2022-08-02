package com.examples.imageloaderlibraryfilters.gpu;

import android.content.Context;
import android.graphics.Bitmap;

import com.examples.imageloaderlibrary.decoder.ImageDecoder;
import com.examples.imageloaderlibrary.decoder.ImageDecoderImpl;
import com.examples.imageloaderlibrary.imagesource.ImageSource;
import com.examples.imageloaderlibrary.util.ImageSize;

import java.io.IOException;

import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;

/**
 * Created by Mariusz
 */
public abstract class GPUImageTwoInputBitmapTransformation extends GPUImageBitmapTransformation<GPUImageTwoInputFilter> {
    private final ImageSource source;

    public GPUImageTwoInputBitmapTransformation(Context context, ImageSource source) {
        super(context);
        this.source = source;
    }

    @Override
    protected void onGPUImageFilterCreated(GPUImageTwoInputFilter filter) {
        ImageDecoder decoder = new ImageDecoderImpl();
        Bitmap decodedBitmap = null;
        try {
            decodedBitmap = decoder.decode(source, ImageSize.ORIGINAL, Bitmap.Config.RGB_565);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filter.setBitmap(decodedBitmap);
    }
}
