package com.soul.customviewdemo.test;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.soul.customviewdemo.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.wight
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/6/5 10:57
 */

public class VoiceAnimalView extends View {

    private Paint mBackPaint;
    private Resources mRes;
    /**
     * 语音
     */
    private Bitmap mVoiceBitmap;
    /**
     * 时间
     */
    private float mDuration = 4000;
    /**
     * 初始圆的半径
     */
    private float mInitialRadius = 45;
    /**
     * 圆的最大半径
     */
    private float mMaxRadius = 200;

    /**
     * 圆的集合
     */
    private List<Circle> mCircleList = new ArrayList<Circle>();
    /**
     * 是否启动中
     */
    private boolean mIsRunning;


    /**
     * 间隔时间
     */
    private long mSpeed = 1000;
    /**
     * 创建的时间
     */
    private long mLastCreateTime;
    private Paint mPaint;
    private Interpolator mInterpolator;

    public VoiceAnimalView(Context context) {
        this(context, null);
    }

    public VoiceAnimalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoiceAnimalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRes = context.getResources();
        mVoiceBitmap = BitmapFactory.decodeResource(mRes, R.drawable.voice_n);
        mPaint = new Paint();
        mBackPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBackPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(context, R.color.circleColor));
        mBackPaint.setColor(ContextCompat.getColor(context, R.color.circleColor2));
        mPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mInterpolator = new LinearInterpolator();
        start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Iterator<Circle> iterator = mCircleList.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            if (System.currentTimeMillis() - circle.mCreateTime < mDuration) {
                mPaint.setAlpha(circle.getAlpha());
                canvas.drawCircle(0, 0, circle.getCurrentRadius(), mPaint);
                mBackPaint.setAlpha(circle.getAlpha());
                canvas.drawCircle(0, 0, circle.getCurrentRadius(), mBackPaint);
            } else {
                iterator.remove();
            }
        }
        if (mCircleList.size() > 0) {
            postInvalidateDelayed(10);
        }

    }

    public void start() {
        if (!mIsRunning) {
            mIsRunning = true;
            mCreateCircle.run();
        }
    }


    private Runnable mCreateCircle = new Runnable() {
        @Override
        public void run() {
            if (mIsRunning) {
                newCircle();
                postDelayed(mCreateCircle, mSpeed); // 每隔mSpeed毫秒创建一个圆
            }
        }
    };

    private void newCircle() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastCreateTime < mSpeed) {
            return;
        }
        Circle circle = new Circle();
        mCircleList.add(circle);
        invalidate();
        mLastCreateTime = currentTime;
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
        if (mInterpolator == null) {
            mInterpolator = new LinearInterpolator();
        }
    }

    private class Circle {
        private long mCreateTime;

        public Circle() {
            this.mCreateTime = System.currentTimeMillis();
        }

        public int getAlpha() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 255);
        }

        public float getCurrentRadius() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return mInitialRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitialRadius);
        }
    }
}
