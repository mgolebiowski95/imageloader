package com.examples.imageloaderlibrary.decoder;

import android.graphics.Bitmap;

import com.examples.imageloaderlibrary.imagesource.ImageSource;
import com.examples.imageloaderlibrary.util.ImageSize;

import java.io.IOException;

/**
 * Created by Mariusz
 */
public interface ImageDecoder {
    Bitmap decode(ImageSource source, ImageSize targetSize, Bitmap.Config config) throws IOException;
}
