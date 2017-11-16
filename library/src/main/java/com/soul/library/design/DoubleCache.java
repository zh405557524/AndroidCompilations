package com.soul.library.design;

import android.graphics.Bitmap;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/11/13 15:07
 */

public class DoubleCache implements ImageCache {
    ImageCache mImageCache = new MemoryCache();

    ImageCache mDiskCache = new DiskCache();


    public Bitmap get(String url) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }


    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }


}
