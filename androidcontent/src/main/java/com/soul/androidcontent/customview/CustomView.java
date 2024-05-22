package com.soul.androidcontent.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Description: 自定义View
 * Author: zhuMing
 * CreateDate: 2020/6/29 17:54
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/29 17:54
 * UpdateRemark:
 */
public class CustomView extends View {

    private CustomUI mCustomUI;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }


    public void setUI(CustomUI paintUI) {
        mCustomUI = paintUI;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        layoutParams.height = mCustomUI.getHeight();
        setLayoutParams(layoutParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCustomUI != null) {
            mCustomUI.onDraw(canvas);
        }
    }
}
