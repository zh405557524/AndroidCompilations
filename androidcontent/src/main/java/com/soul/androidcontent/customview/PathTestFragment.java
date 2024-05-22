package com.soul.androidcontent.customview;

import android.view.View;

import com.soul.androidcontent.customview.path.BezierView;
import com.soul.androidcontent.customview.path.DragBubbleView;
import com.soul.androidcontent.customview.path.PathMeasureView;
import com.soul.androidcontent.customview.path.PathView;
import com.soul.lib.test.ButtonTextFragment;

/**
 * Description: Path的用法
 * Author: zhuMing
 * CreateDate: 2020/7/3 15:05
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/3 15:05
 * UpdateRemark:
 */
public class PathTestFragment extends ButtonTextFragment implements View.OnClickListener {
    @Override
    public String getClassName() {
        return "Path的用法";
    }

    @Override
    protected void initEvent() {
        addTextName("PathAPI用法", this);
        addTextName("多阶贝塞尔曲线", this);
        addTextName("爆炸气球", this);
        addTextName("path测量的用法", this);
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        View view = null;
        switch (tag) {
            case "PathAPI用法":
                view = new PathView(getContext());
                break;

            case "多阶贝塞尔曲线":
                view = new BezierView(getContext());

                break;
            case "爆炸气球":
                view = new DragBubbleView(getContext());
                break;
            case "path测量的用法":
                view = new PathMeasureView(getContext());
                break;
        }
        addView(view);
    }

}
