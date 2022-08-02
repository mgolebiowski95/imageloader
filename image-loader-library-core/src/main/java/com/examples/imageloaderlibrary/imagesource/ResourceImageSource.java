package com.examples.imageloaderlibrary.imagesource;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class ResourceImageSource implements ImageSource {
    private final Context context;
    private final int id;

    public ResourceImageSource(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public InputStream getStream() throws IOException {
        return context.getResources().openRawResource(id);
    }

    public Context getContext() {
        return context;
    }

    public int getResourceId() {
        return id;
    }

    @Override
    public String toString() {
        return "ResourceImageSource{" +
                "id=" + id +
                '}';
    }
}
