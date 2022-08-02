package com.examples.imageloaderlibrary.imagesource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class UnknownImageSource implements ImageSource {
    @Override
    public InputStream getStream() throws IOException {
        throw new UnsupportedOperationException();
    }
}
