package com.soul.androidcompilptions.rxandretrofi.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.soul.androidcompilptions.R;
import com.soul.library.BaseApplication;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/8 17:27
 */

public class ImageUtil {


    /**
     * 通过uri 设置图片
     *
     * @param imageView
     * @param url
     */
    public static void setImageView(ImageView imageView, String url) {
        if (url == null) {
            Glide.with(BaseApplication.getContext())
                    .load(R.drawable.meinv)
                    .centerCrop()
                    .thumbnail(0.5f)//
                    .dontAnimate() //淡入淡出
                    .skipMemoryCache(true)//设置图片不加入内存缓存
                    .into(imageView);
        } else {
            Uri uri = Uri.parse(url);
            Glide.with(BaseApplication.getContext())
                    .load(uri)
                    .centerCrop()
                    .thumbnail(0.5f)//
                    .dontAnimate() //淡入淡出
                    .skipMemoryCache(true)
                    .into(imageView);
        }
    }


    /**
     * 通过uri 设置圆形图片图片
     *
     * @param imageView
     * @param url
     */
    public static void setCircleImageView(final ImageView imageView, String url) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .dontAnimate() //淡入淡出
                .skipMemoryCache(true)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

}
