package com.examples.imageloaderlibrary.imagesource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class ByteArrayImageSource implements ImageSource {
    private final byte[] buf;

    public ByteArrayImageSource(byte[] buf) {
        this.buf = buf;
    }

    @Override
    public InputStream getStream() throws IOException {
        return new ByteArrayInputStream(buf);
    }

    @Override
    public String toString() {
        return "ByteArrayImageSource{" +
                "bufSize=" + buf.length +
                '}';
    }
}
