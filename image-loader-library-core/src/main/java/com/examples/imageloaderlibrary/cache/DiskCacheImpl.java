package com.examples.imageloaderlibrary.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.examples.imageloaderlibrary.Build;
import com.examples.imageloaderlibrary.BuildConfig;
import com.examples.imageloaderlibrary.logger.Logcat;
import com.examples.imageloaderlibrary.util.PrettyPrint;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Mariusz
 */
public class DiskCacheImpl implements DiskCache {
    /** 250 MB of cache. */
    private static final int DEFAULT_DISK_CACHE_SIZE = 250 * 1024 * 1024;
    private static final String DEFAULT_DISK_CACHE_DIR = "image_manager_disk_cache";

    private static final int IO_BUFFER_SIZE_BYTE = 1024 * 8;
    private Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;

    private DiskLruCache diskLruCache;
    private String directory;

    public DiskCacheImpl(Context context) {
        directory = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || (!Environment.isExternalStorageRemovable() && context.getExternalCacheDir() != null) ?
                context.getExternalCacheDir().getPath() : context.getCacheDir().getPath();
    }

    @Override
    public File getDirectory() {
        return null;
    }

    @Override
    public Bitmap get(String key, Bitmap.Config config) {
        if (key == null)
            throw new NullPointerException("key == null");
        Bitmap bitmap = getFromDiskCache(key, config);
        if (Build.DEBUG && bitmap != null)
            Logcat.DEBUG.log(this, "Disk cache hit");
        return bitmap;
    }

    @Override
    public boolean save(String key, Bitmap value) throws IOException {
        if (key == null || value == null)
            throw new NullPointerException("key == null || value == null");
        boolean b = putBitmapToDiskCache(value, key);
        long size = diskLruCache.size();
        long maxSize = diskLruCache.getMaxSize();
        if (Build.DEBUG)
            Logcat.DEBUG.log(this, "DISK_CACHE[allocated:" + size + "KiB, free:" + (maxSize - size) + "KiB, used:" + PrettyPrint.percentOf(size, maxSize) + "]");
        return b;
    }

    @Override
    public boolean remove(String key) {
        purge(key);
        return false;
    }

    @Override
    public void close() {
        if (!diskLruCache.isClosed())
            try {
                diskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void clear() {
        clearDiskCache();
    }

    @Override
    public int size() {
        return (int) diskLruCache.size();
    }

    @Override
    public int maxSize() {
        return (int) diskLruCache.getMaxSize();
    }

    @Override
    public void setCompressFormat(Bitmap.CompressFormat format) {
        this.format = format;
    }

    public DiskLruCache getDiskCache() {
        if (diskLruCache == null) {
            try {
                diskLruCache = DiskLruCache.open(new File(directory, DEFAULT_DISK_CACHE_DIR), BuildConfig.VERSION_CODE, 1, DEFAULT_DISK_CACHE_SIZE);
            } catch (Exception e) {
                Logcat.ERROR.log(this, "Could not create disk diskLruCache", e);
            }
        }
        return diskLruCache;
    }

    public Bitmap getFromDiskCache(String cacheKey, Bitmap.Config config) {
        if (getDiskCache() != null) {
            try {
                DiskLruCache.Snapshot snapshot = getDiskCache().get(cacheKey);
                if (snapshot != null) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inMutable = true;
                    options.inPreferredConfig = config;
                    return BitmapFactory.decodeStream(snapshot.getInputStream(0), null, options);
                }
            } catch (IOException e) {
                Logcat.ERROR.log(this, "Could not read from disk diskLruCache", e);
            }
        }
        return null;
    }

    public boolean putBitmapToDiskCache(Bitmap bitmap, String cacheKey) {
        if (getDiskCache() != null) {
            OutputStream out = null;
            try {
                DiskLruCache.Editor editor = getDiskCache().edit(cacheKey);

                if (editor != null) {
                    out = new BufferedOutputStream(editor.newOutputStream(0), IO_BUFFER_SIZE_BYTE);
                    if (bitmap.compress(format, 100, out)) {
                        editor.commit();
                        return true;
                    } else {
                        Logcat.WARN.log("Could not compress png for disk cache");
                        editor.abort();
                    }
                }
            } catch (Exception e) {
                Logcat.ERROR.log(this, "Could not save outputStream for disk cache", e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        Logcat.ERROR.log(this, "Could not close outputStream while writing cache", e);
                    }
                }
            }
        }
        return false;
    }

    public synchronized void clearDiskCache() {
        if (diskLruCache != null) {
            try {
                diskLruCache.delete();
            } catch (IOException e) {
                Logcat.ERROR.log(this, "Could not clear diskcache", e);
            }
            diskLruCache = null;
        }
    }

    public void purge(String cacheKey) {
        try {
            if (diskLruCache != null)
                diskLruCache.remove(cacheKey);
        } catch (Exception e) {
            Logcat.ERROR.log(this, "Could not remove entry in cache purge", e);
        }
    }
}
