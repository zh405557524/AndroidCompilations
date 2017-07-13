package com.soul.androidcompilptions.customview.customImageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.soul.androidcompilptions.R;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.customview.customImageview
 * @作者：祝明
 * @描述：自定义音量
 * @创建时间：2016/11/20 17:40
 */

public class CustomVolumControlBar extends View {

    private int mFirstColor;
    private int mSecondColor;
    private Bitmap mImage;
    private int mCircleWidth;
    private int mCount;
    private int mSplitSize;
    private Paint mPaint;
    private Rect mRect;
    /**
     * 当前进度
     */
    private int mCurrentCount = 3;

    public CustomVolumControlBar(Context context) {
        this(context, null);
    }

    public CustomVolumControlBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVolumControlBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar, defStyleAttr, 0);
        //第一个颜色
        mFirstColor = a.getColor(R.styleable.CustomVolumControlBar_firstColor, Color.GREEN);
        //第二圈的颜色
        mSecondColor = a.getColor(R.styleable.CustomVolumControlBar_secondColor, Color.RED);
        //中间的图片
        mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.CustomVolumControlBar_bg, 0));
        //圈的宽度
        mCircleWidth = a.getDimensionPixelSize(R.styleable.CustomVolumControlBar_circleWidth,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics())
        );
        //个数
        mCount = a.getInt(R.styleable.CustomVolumControlBar_dotCount, 20);
        //每个块块间的间隙
        mSplitSize = a.getInt(R.styleable.CustomVolumControlBar_splitSize, 20);

        a.recycle();
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStrokeWidth(mCircleWidth);//设置圆环的宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND);//定义线段断电形状为圆头
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        int centre = getWidth() / 2;//获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;//半径

        /**
         * 画块
         */
        drawOval(canvas, centre, radius);
        /**
         * 计算内切正方形的位置
         */
        int relRadius = radius - mCircleWidth / 2;//获得内圆的半径
        /**
         * 内切正方形的距离顶部=  mCircleWidth + relRadius -v2/2
         */
        mRect.left = (int) ((relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth);
        /**
         * 内切正方形的距离左边 = mCircleWidth + relRadius - v2 /2
         */
        mRect.top = (int) ((relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth);
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        /**
         * 如果图片比较小，那么根据图片的尺寸放置到正中心
         */
        if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mImage.getWidth());
            mRect.bottom = (int) (mRect.top + mImage.getHeight());

        }

        canvas.drawBitmap(mImage, null, mRect, mPaint);


    }


    /**
     * 根据参数画出每个小块
     *
     * @param canvas
     * @param centre
     * @param radius
     */
    private void drawOval(Canvas canvas, int centre, int radius) {
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;
        RectF oval = new RectF(centre - radius, centre - radius,
                centre + radius, centre + radius);//用于定义圆弧的形状和大小界限

        mPaint.setColor(mFirstColor);//设置圆环的颜色
        for (int i = 0; i < mCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint);//根据进度画圆弧
        }

        mPaint.setColor(mSecondColor);//设置圆环的颜色
        for (int i = 0; i < mCurrentCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint);//根据进度画圆弧
        }
    }

    /**
     * 当前数量1
     */
    public void up() {
        mCurrentCount++;
        postInvalidate();
    }

    /**
     * 当前数量-1
     */
    public void down() {
        mCurrentCount--;
        postInvalidate();
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                xUp = (int) event.getY();
                if (xUp > xDown) {//下滑
                    down();
                } else {
                    up();
                }
                break;
        }
        return true;
    }
}































