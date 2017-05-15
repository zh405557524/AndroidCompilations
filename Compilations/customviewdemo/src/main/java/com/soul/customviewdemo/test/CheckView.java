package com.soul.customviewdemo.test;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.soul.customviewdemo.R;
import com.soul.library.utils.UIUtils;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.test
 * @作者：祝明
 * @描述：按钮
 * @创建时间：2017/3/15 15:49
 */

public class CheckView extends View {


    private int currentPage = -1;//当前页码
    private int maxPage = 13;//当前页码
    private int animalTime = 300;
    private int mWidth;
    private int mHeight;
    private boolean isCheck;//状态
    private boolean isAnimal;
    private Bitmap mCheckView;
    private Paint mPaint;
    private Resources mRes;
    private int mCheckBackColor;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRes = context.getResources();
        mPaint = new Paint();
        mCheckBackColor = ContextCompat.getColor(context, R.color.checkBack);
        mCheckView = BitmapFactory.decodeResource(mRes, R.drawable.checkmark);
        mWidth = mCheckView.getWidth();
        mHeight = mCheckView.getHeight();
        isAnimal = false;
        isCheck = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = mWidth / maxPage + getPaddingLeft() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = mHeight + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPage < 0) {
            currentPage = 0;
        }
        if (currentPage > maxPage) {
            currentPage = maxPage;
        }
        int bitmapWidth = mWidth / maxPage;
        // 指定图片绘制区域
        Rect rect = new Rect(bitmapWidth * (currentPage - 1), 0,
                bitmapWidth * currentPage, mHeight);
        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(getPaddingLeft(), getPaddingTop(), mWidth / maxPage + getPaddingLeft(),
                mHeight + getPaddingTop());
        mPaint.setColor(mCheckBackColor);
        canvas.drawCircle(bitmapWidth / 2 + getPaddingLeft(), bitmapWidth / 2 + getPaddingTop(),
                bitmapWidth / 2, mPaint);
        canvas.drawBitmap(mCheckView, rect, dst, mPaint);
    }

    /**
     * 选择
     */
    public void check() {
        if (!isCheck && !isAnimal) {
            checkAnimation(0, 13);
            isCheck = true;
        }
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (isCheck && !isAnimal) {
            checkAnimation(13, 0);
            isCheck = false;
        }
    }

    /**
     * 获取当前的状态
     *
     * @return
     */
    public boolean isCheck() {
        return isCheck;
    }

    private synchronized void checkAnimation(int start, final int end) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(animalTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int page = (int) animation.getAnimatedValue();
                currentPage = page;
                UIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        invalidate();
                    }
                });
                if (page == end) {
                    isAnimal = false;
                }
            }
        });
        valueAnimator.start();
        isAnimal = true;
    }


}
