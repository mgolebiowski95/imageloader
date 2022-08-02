package com.examples.imageloaderlibrary.imagesource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mariusz
 */
public class FileImageSource implements ImageSource {
    private String name;

    public FileImageSource(String name) {
        this.name = name;
    }

    public FileImageSource(File file) {
        this.name = file.getPath();
    }

    @Override
    public InputStream getStream() throws IOException {
        return new FileInputStream(name);
    }

    @Override
    public String toString() {
        return "FileImageSource{" +
                "name='" + name + '\'' +
                '}';
    }
}
