package com.soul.androidcompilptions.customview.canvas_study;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/12/28 10:02
 *
 * Canvas 操作类型
 *
 * 绘制图片
 *          1 drawPicture
 *              API :
 *                   |-public int getWidth()            获取宽度
 *                   |-public int getHeight()           获取高度
 *                   |-public Canvas beginRecording(int
 *                                    width,int height) 开始录制(返回一个Canvas，在Canvas中所有的绘制都会存在Picture中)
 *                   |-
 *
 *          2 drawBitmap
 *
 *
 *
 */

public class CanvasPictureAndBitmap extends View {
    public CanvasPictureAndBitmap(Context context) {
        this(context,null);
    }

    public CanvasPictureAndBitmap(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasPictureAndBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



    }
}
