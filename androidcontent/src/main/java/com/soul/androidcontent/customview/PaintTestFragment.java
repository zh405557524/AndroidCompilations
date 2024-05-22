package com.soul.androidcontent.customview;

import android.view.View;

import com.soul.androidcontent.customview.paint.ColorFilterUI;
import com.soul.androidcontent.customview.paint.GradientPaintUI;
import com.soul.androidcontent.customview.paint.XFerModesUI;
import com.soul.lib.test.ButtonTextFragment;

/**
 * Description: paint使用示例
 * Author: zhuMing
 * CreateDate: 2020/6/29 17:19
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/29 17:19
 * UpdateRemark:
 */
public class PaintTestFragment extends ButtonTextFragment implements View.OnClickListener {

    @Override
    public String getClassName() {
        return "paint使用示例";
    }

    @Override
    protected void initEvent() {
        addTextName("paint常用API", this);
        addTextName("混合图层的用法", this);
        addTextName("滤镜", this);
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        CustomUI customUI = null;
        switch (tag) {
            case "paint常用API":
                customUI = new GradientPaintUI();
                break;
            case "混合图层的用法":
                customUI = new XFerModesUI(getContext());
                break;
            case "滤镜":
                customUI = new ColorFilterUI(getContext());
                break;

        }

        CustomView customView = new CustomView(getContext());
        customView.setUI(customUI);
        addView(customView);
    }

}
