package com.soul.library.design;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @描述：内存缓存
 * @作者：祝明
 * @创建时间：2017/11/14 10:56
 */

public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mImageCache;

    public MemoryCache() {
        initImageCache();
    }

    private void initImageCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
