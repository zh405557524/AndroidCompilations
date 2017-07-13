package com.soul.androidcompilptions.customview.customImageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.soul.androidcompilptions.R;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.customview.customImageview
 * @作者：祝明
 * @描述：自定义圆环
 * @创建时间：2016/11/20 16:42
 */

public class CustomProgressBar extends View {

    private final int mCircleWidth;
    private int mFirstColor;
    private int mSecondColor;
    private int mSendcondColor;
    private int mSpeed;
    private Paint mPaint;
    public int mProgress;
    private boolean isNext;


    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
        mFirstColor = a.getColor(R.styleable.CustomProgressBar_firstColor, Color.GREEN);
        mSecondColor = a.getColor(R.styleable.CustomProgressBar_secondColor, Color.RED);
        mCircleWidth = a.getDimensionPixelSize(R.styleable.CustomProgressBar_circleWidth, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()
        ));
        mSpeed = a.getIndex(R.styleable.CustomProgressBar_speed);
        a.recycle();
        mPaint = new Paint();
        //绘图线程
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centre = getWidth() / 2;//获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;//半径
        mPaint.setStrokeWidth(mCircleWidth);//设置圆环的宽度
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius,
                centre + radius);//用于定义圆弧的形状和大小的界限。

        if (!isNext){
        //第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor);//设置圆环的颜色
            canvas.drawCircle(centre,centre,radius,mPaint);//画出圆环
            mPaint.setColor(mSecondColor);//设置圆环的颜色
            canvas.drawArc(oval,-90,mProgress,false,mPaint);//根据进度画圆弧
        }else {
            mPaint.setColor(mSecondColor);//设置圆环的颜色
            canvas.drawCircle(centre,centre,radius,mPaint);//画出圆环
            mPaint.setColor(mFirstColor);//设置圆环的颜色
            canvas.drawArc(oval,-90,mProgress,false,mPaint);//根据进度画圆弧
        }

    }
}






































