package com.soul.androidcompilptions.customview.customImageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.soul.androidcompilptions.R;

import java.util.HashSet;
import java.util.Random;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.customview.customImageview
 * @作者：祝明
 * @描述：自定义文字
 * @创建时间：2016/11/20 11:43
 */

public class CustomTitleViw extends View {

    private String mTitlteText;
    private int mTitleTextColor;
    private Paint mPaint;
    private int mTitleTextSize;
    private Rect mBound;

    public CustomTitleViw(Context context) {
        this(context, null);
    }

    public CustomTitleViw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleViw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们所定义的自定义样式属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    mTitlteText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    //默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    //默认设置为16sp，TypeVlues也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension
                            (TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitlteText, 0, mTitlteText.length(), mBound);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitlteText = randomText();
                postInvalidate();
            }
        });
    }


    private String randomText() {
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }
        return sb.toString();

    }

    /**
     * MeasureSpec 的specMode 的三种类型
     * <p>
     * EXACTLY:一般是设置了明确的值或者是MATHCH_PARENT
     * AT_MOST:表示子布局限制在一个最大值内，一般为WARP_CONTENT
     * UNSPECIFIED:表示子布局想要多大就多大，很少使用
     */
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
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitlteText, 0, mTitlteText.length(), mBound);
            int textWidth = mBound.width();
            int desired = getPaddingLeft() + textWidth + getPaddingRight();
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitlteText, 0, mTitlteText.length(), mBound);
            int textHeight = mBound.height();
            int desired = getPaddingTop() + textHeight + getPaddingBottom();
            height = desired;
        }
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        //画线框
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        //设置颜色
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitlteText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}









