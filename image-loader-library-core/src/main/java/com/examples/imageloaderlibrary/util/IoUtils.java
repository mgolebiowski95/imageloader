package com.examples.imageloaderlibrary.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mariusz Gołębiowski
 */
public class IoUtils {
    private static final int BUFFER = 16;

    public static byte[] read(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[BUFFER];
            int read = inputStream.read(buffer);
            while (read != -1) {
                byteArrayOutputStream.write(buffer, 0, read);
                read = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeOutputStreams(byteArrayOutputStream);
            closeInputStream(inputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void write(OutputStream outputStream, byte[] content) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(content);
            byte[] buffer = new byte[BUFFER];
            int read = byteArrayInputStream.read(buffer);
            while (read != -1) {
                outputStream.write(buffer, 0, read);
                read = byteArrayInputStream.read(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeInputStream(byteArrayInputStream);
            closeOutputStreams(outputStream);
        }
    }

    public static void write(OutputStream outputStream, InputStream inputStream) {
        try {
            byte[] buffer = new byte[BUFFER];
            int read = inputStream.read(buffer);
            while (read != -1) {
                outputStream.write(buffer, 0, read);
                read = inputStream.read(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeInputStream(inputStream);
            closeOutputStreams(outputStream);
        }
    }

    public static void closeInputStream(InputStream... inputStreams) {
        for (InputStream inputStream : inputStreams) {
            if (inputStream != null) try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeOutputStreams(OutputStream... outputStreams) {
        for (OutputStream outputStream : outputStreams) {
            if (outputStream != null) try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile())
            file.delete();
    }
}
