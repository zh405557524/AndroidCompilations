package com.soul.library.design;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/11/13 10:19
 */

public class DiskCache implements ImageCache {

    static String cacheDir = "sdcard/cache/";


    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
