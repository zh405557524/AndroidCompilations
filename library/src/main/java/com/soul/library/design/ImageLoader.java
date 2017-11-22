package com.soul.library.design;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @描述：图片缓存类
 * @作者：祝明
 * @创建时间：2017/11/9 19:41
 */

public class ImageLoader {

    public static ImageLoader sImageLoader;
    //内存缓存
    private ImageCache mImageCache = new MemoryCache();
    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();

    DoubleCache mDoubleCache = new DoubleCache();

    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader() {

    }

    /**
     * @return
     */
    public static ImageLoader getInstants() {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (sImageLoader == null) {
                    sImageLoader = new ImageLoader();
                }
            }
        }
        return sImageLoader;
    }

    public void setImageCache(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    public void displayImage(String url, ImageView imageView) {
        imageView.setTag(url);
        Bitmap bitmapCache = mImageCache.get(url);
        if (bitmapCache != null) {
            imageView.setImageBitmap(bitmapCache);
            return;
        }
        mExecutorService.submit(() -> {
            Bitmap bitmap = downloadImage(url);
            if (bitmap == null) {
                return;
            }
            if (imageView.getTag().equals(url)) {
                imageView.setImageBitmap(bitmap);
            }
            mImageCache.put(url, bitmap);
        });

    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
