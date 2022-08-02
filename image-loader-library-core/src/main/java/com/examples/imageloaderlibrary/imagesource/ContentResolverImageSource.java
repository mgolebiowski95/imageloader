package com.examples.imageloaderlibrary.imagesource;

import android.content.ContentResolver;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class ContentResolverImageSource implements ImageSource {
    private final ContentResolver contentResolver;
    private final Uri uri;

    public ContentResolverImageSource(ContentResolver contentResolver, Uri uri) {
        this.contentResolver = contentResolver;
        this.uri = uri;
    }

    @Override
    public InputStream getStream() throws IOException {
        return contentResolver.openInputStream(uri);
    }

    @Override
    public String toString() {
        return "ContentResolverImageSource{" +
                "uri=" + uri +
                '}';
    }
}
