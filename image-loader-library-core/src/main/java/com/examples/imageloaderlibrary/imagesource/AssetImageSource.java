package com.examples.imageloaderlibrary.imagesource;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class AssetImageSource implements ImageSource {
    private final AssetManager assetManager;
    private final String fileName;

    public AssetImageSource(AssetManager assetManager, String fileName) {
        this.assetManager = assetManager;
        this.fileName = fileName;
    }

    @Override
    public InputStream getStream() throws IOException {
        return assetManager.open(fileName);
    }

    @Override
    public String toString() {
        return "AssetImageSource{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
