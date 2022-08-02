package com.examples.imageloaderlibrary.cache;

import android.graphics.Bitmap;

import androidx.collection.LruCache;

import com.examples.imageloaderlibrary.Build;
import com.examples.imageloaderlibrary.logger.Logcat;
import com.examples.imageloaderlibrary.util.PrettyPrint;
import com.examples.imageloaderlibrary.util.SDKVersion;

/**
 * Created by Mariusz
 */
public class MemoryCacheImpl implements MemoryCache {
    // Default memory cache size in kilobytes
    private static final int DEFAULT_MEM_CACHE_SIZE = 1024 * 5; // 5MB

    private LruCache<String, Bitmap> cache;
    private int cacheSize;

    public MemoryCacheImpl() {
        setMemCacheSizePercent(0.25f);
        cache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                final int bitmapSize = getBitmapSize(value) / 1024;
                return bitmapSize == 0 ? 1 : bitmapSize;
            }
        };
    }

    @Override
    public Bitmap get(String key) {
        if (key == null)
            throw new NullPointerException("key == null");
        Bitmap bitmap = cache.get(key);
        if (Build.DEBUG && bitmap != null)
            Logcat.DEBUG.log(this, "Memory cache hit");
        return bitmap;
    }

    @Override
    public Bitmap put(String key, Bitmap value) {
        if (key == null || value == null)
            throw new NullPointerException("key == null || value == null");
        Bitmap bitmap = cache.put(key, value);
        int size = cache.size();
        int maxSize = cache.maxSize();
        if (Build.DEBUG)
            Logcat.DEBUG.log(this, "MEMORY_CACHE[allocated:" + size + "KiB, free:" + (maxSize - size) + "KiB, used:" + PrettyPrint.percentOf(size, maxSize) + "]");
        return bitmap;
    }

    @Override
    public Bitmap remove(String key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.evictAll();
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public int maxSize() {
        return cache.maxSize();
    }

    public void setMemCacheSizePercent(float percent) {
        if (percent < 0.01f || percent > 0.8f) {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be "
                                                       + "between 0.01 and 0.8 (inclusive)");
        }
        cacheSize = Math.round(percent * Runtime.getRuntime().maxMemory() / 1024);
    }

    private static int getBitmapSize(Bitmap bitmap) {
        if (SDKVersion.greaterOrEqualsThan(android.os.Build.VERSION_CODES.KITKAT))
            return bitmap.getAllocationByteCount();
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
