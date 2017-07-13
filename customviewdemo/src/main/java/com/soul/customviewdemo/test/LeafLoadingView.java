package com.soul.customviewdemo.test;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.soul.customviewdemo.R;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.test
 * @作者：祝明
 * @描述：进度条
 * @创建时间：2017/3/16 10:25
 */

public class LeafLoadingView extends View {
    private final static int  SIDING = 10;

    /**
     * 叶子
     */
    private Bitmap mLeafBitmap;
    /**
     * 框
     */
    private Bitmap mKuangBitmap;
    /**
     * 风扇
     */
    private Bitmap mFenshanBitmap;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 进度条底部的颜色
     */
    private int mProgressBack;
    private int mProgressColor;

    public LeafLoadingView(Context context) {
        this(context, null);
    }

    public LeafLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeafLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Resources res = context.getResources();
        mLeafBitmap = BitmapFactory.decodeResource(res, R.drawable.leaf);
        mKuangBitmap = BitmapFactory.decodeResource(res, R.drawable.leaf_kuang);
        mFenshanBitmap = BitmapFactory.decodeResource(res, R.drawable.fengshan);

        mPaint = new Paint();
        mProgressBack = ContextCompat.getColor(context, R.color.progressBack);
        mProgressColor = ContextCompat.getColor(context, R.color.progressColor);
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {//精确尺寸
            width = widthSize;
        } else {
            width = mKuangBitmap.getWidth() + getPaddingLeft() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = mKuangBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mKuangBitmap == null) {
            return;
        }

        //绘制框
        //1指定图片绘制区域
        Rect rec = new Rect(0, 0, mKuangBitmap.getWidth(), mKuangBitmap.getHeight());
        //2 指定图片在屏幕显示区域
        Rect dst = new Rect(getPaddingLeft(), getPaddingTop(), mKuangBitmap.getWidth() +
                getPaddingLeft(), mKuangBitmap.getHeight() + getPaddingTop());
        canvas.drawBitmap(mKuangBitmap, rec, dst, mPaint);

        //绘制progress
        //1 绘制圆弧
        RectF rectF = new RectF(0+SIDING,0+SIDING,150,mKuangBitmap.getHeight()-SIDING);
        mPaint.setColor(mProgressColor);
        canvas.drawArc(rectF,-90,-180,true,mPaint);

        //2 绘制半圆



        //3 绘制矩形



    }


}
