package com.soul.customviewdemo.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 描述：
 * 作者：祝明
 * 创建时间：2017/4/5 21:10
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet p) {

        return new MarginLayoutParams(getContext(),p);
    }





    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }



}
