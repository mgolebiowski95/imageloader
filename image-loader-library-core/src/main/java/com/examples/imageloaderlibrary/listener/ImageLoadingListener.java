package com.examples.imageloaderlibrary.listener;

import android.graphics.Bitmap;

/**
 * Created by Mariusz
 */
public interface ImageLoadingListener {
    void onLoadingStarted();

    void onLoadingFailed(Throwable cause);

    void onLoadingComplete(Bitmap loadedBitmap);
}
