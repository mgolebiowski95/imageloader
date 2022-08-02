package com.examples.imageloaderlibrary.util;

/**
 * Created by Mariusz
 */

public class ImageSizeUtils {
    public static int computeImageSampleSize(ImageSize srcSize, ImageSize targetSize) {
        int inSampleSize = 1;
        final int width = srcSize.getWidth();
        final int height = srcSize.getHeight();

        while (width / inSampleSize > targetSize.getWidth() || height / inSampleSize > targetSize.getHeight())
            inSampleSize *= 2;
//        while ((width / inSampleSize) * (height / inSampleSize) > targetSize.getWidth() * targetSize.getHeight())
//            inSampleSize *= 2;
        return inSampleSize;
    }
}
