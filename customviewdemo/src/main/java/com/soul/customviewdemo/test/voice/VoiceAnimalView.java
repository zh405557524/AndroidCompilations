package com.soul.customviewdemo.test.voice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @作者：祝明
 * @创建时间：2017/11/14 13:58
 */

public class VoiceAnimalView extends View {

    /**
     * 直线路径的集合
     */
    List<Curve> mCurveList = new ArrayList<>();
    private Paint mPaint;

    public VoiceAnimalView(Context context) {
        this(context, null);
    }

    public VoiceAnimalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoiceAnimalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        addCurve(new Curve(getContext(), w, h));
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void addCurve(Curve curve) {
        mCurveList.add(curve);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Curve curve : mCurveList) {
            curve.onDraw(canvas, mPaint);
        }
    }


}
