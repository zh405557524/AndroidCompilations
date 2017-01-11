package com.soul.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @作者：祝明
 * @描述：滑动Tab
 * @创建时间：2016/11/7 15:56
 */

public class SimpleViewPagerIndicator extends LinearLayout {

    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    private static final int COLOR_INDICATOR_COLOR = Color.GREEN;

    private int mIndicatorColor = COLOR_INDICATOR_COLOR;
    private Paint mPaint = new Paint();
    private String[] mTitles;
    private int mTabCount;
    private float mTranslationX;
    private float mTabWidth;

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setColor(mIndicatorColor);
        mPaint.setStrokeWidth(9.0F);
    }

    public void setTitles(String[] titles) {
        mTitles = titles;
        mTabCount = titles.length;
        generateTitleView();
    }

    private void generateTitleView() {
        if (getChildCount() > 0)
            this.removeAllViews();

        int count = mTitles.length;
        setWeightSum(count);

        for (int i = 0; i < count; i++) {
            TextView tv = new TextView(getContext());
            LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(COLOR_TEXT_NORMAL);
            tv.setText(mTitles[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setLayoutParams(lp);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            addView(tv);
        }
    }

    public void scroll(int position, float positionOffset) {
        /**
         * 0-1;positons  = 0;1-0:positon = 0;
         */
        mTranslationX = getWidth() / mTabCount * (position + positionOffset);
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight() - 2);
        canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
        canvas.restore();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        if (mTabCount != 0)
            mTabWidth = w / mTabCount;
    }


    public void setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
    }
}
