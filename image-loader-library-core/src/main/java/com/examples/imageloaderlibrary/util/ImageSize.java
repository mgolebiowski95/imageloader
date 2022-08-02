package com.examples.imageloaderlibrary.util;

/**
 * Created by Mariusz
 */

public class ImageSize {
    private final int width;
    private final int height;

    public static final ImageSize ORIGINAL = new ImageSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "ImageSize{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
