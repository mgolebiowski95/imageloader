package com.examples.imageloaderlibrary.util;

import com.examples.imageloaderlibrary.imagesource.ImageSource;
import com.examples.imageloaderlibrary.task.ImageLoadingOptions;

import java.security.MessageDigest;

/**
 * Created by Mariusz
 */
public class KeyTool implements KeyGenerator{
    private ImageSource source;
    private ImageLoadingOptions options;

    public ImageSource getSource() {
        return source;
    }

    public void setSource(ImageSource source) {
        this.source = source;
    }

    public ImageLoadingOptions getOptions() {
        return options;
    }

    public void setOptions(ImageLoadingOptions options) {
        this.options = options;
    }


    public static String getCacheKey(String text) {
        return sha1Hash(text);
    }

    private static String sha1Hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            byte[] sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (Exception e) {
            throw new RuntimeException("Could not hash",e);
        }
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    @Override
    public String generateKey() {
        String sourceKey = source.toString();
        String optionsKey = options.toString();
        return sourceKey + " " + optionsKey;
    }
}
