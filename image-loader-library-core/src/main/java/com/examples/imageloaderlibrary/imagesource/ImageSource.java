package com.examples.imageloaderlibrary.imagesource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public interface ImageSource {
    InputStream getStream() throws IOException;
}
