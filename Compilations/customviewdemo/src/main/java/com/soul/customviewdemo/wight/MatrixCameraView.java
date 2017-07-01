package com.soul.customviewdemo.wight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.soul.customviewdemo.R;

/**
 * @描述：view的相机
 * @作者：祝明
 * @创建时间：2017/7/1 9:24
 */


/**
 * 一 基本方法 save ,restore 保存，回滚
 * 二 常用方法 getMatrix,applyToCanvas 获取Matrix,应用到画布
 * 三 translate 位移
 * 四 rotat rotateX ,rotateY,rotateZ 各种旋转
 * 五 setLocation,getLocationX getLocationY ,getLocationZ设置与获取相机的位置
 */
public class MatrixCameraView extends FrameLayout {
    public MatrixCameraView(Context context) {
        this(context, null);
    }

    public MatrixCameraView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixCameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = View.inflate(getContext(), R.layout.text_image, null);
        addView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //计算中心点
                float centerX = v.getWidth() / 2.0f;
                float centerY = v.getHeight() / 2.0f;
                //括号内参数分别为(上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲)
                Rotate3dAnimation rotation = new Rotate3dAnimation(getContext(), 0,
                        180, centerX, centerY, 0f, true);

                rotation.setDuration(3000);
                rotation.setFillAfter(true);
                rotation.setInterpolator(new LinearInterpolator());
                v.startAnimation(rotation);
            }
        });


    }
}
