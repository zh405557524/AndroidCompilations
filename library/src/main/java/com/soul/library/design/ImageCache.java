package com.soul.library.design;

import android.graphics.Bitmap;

/**
 * @描述：图片加载逻辑
 * @作者：祝明
 * @创建时间：2017/11/9 20:20
 */

public interface ImageCache {

    void put(String url, Bitmap bitmap);

    Bitmap get(String url);

}
