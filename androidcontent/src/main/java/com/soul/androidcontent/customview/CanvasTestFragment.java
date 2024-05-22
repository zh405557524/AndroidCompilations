package com.soul.androidcontent.customview;

import android.view.View;

import com.soul.androidcontent.customview.canvas.CaseCanvasUI;
import com.soul.androidcontent.customview.canvas.DrawCanvasUI;
import com.soul.androidcontent.customview.canvas.TransformUI;
import com.soul.lib.test.ButtonTextFragment;

/**
 * Description: Canvas使用示例
 * Author: zhuMing
 * CreateDate: 2020/7/2 10:00
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/2 10:00
 * UpdateRemark:
 */
public class CanvasTestFragment extends ButtonTextFragment implements View.OnClickListener {
    @Override
    public String getClassName() {
        return "Canvas使用示例";
    }

    @Override
    protected void initEvent() {
        addTextName("canvas的绘制操作", this);
        addTextName("canvas的变化操作", this);
        addTextName("canvas几个小案例", this);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        CustomUI customUI = null;
        switch (tag) {

            case "canvas的绘制操作":
                customUI = new DrawCanvasUI(getContext());
                break;

            case "canvas的变化操作":
                customUI = new TransformUI(getContext());
                break;

            case "canvas几个小案例":
                customUI = new CaseCanvasUI(getContext());
                break;
        }

        CustomView customView = new CustomView(getContext());
        customView.setUI(customUI);
        addView(customView);
    }
}
