package com.soul.androidcontent.customview;

import android.view.View;

import com.soul.androidcontent.customview.matrix.CameraView;
import com.soul.androidcontent.customview.matrix.MatrixCameraView;
import com.soul.androidcontent.customview.matrix.MatrixView;
import com.soul.lib.test.ButtonTextFragment;

/**
 * Description: Matrix使用示例
 * Author: zhuMing
 * CreateDate: 2020/7/6 18:22
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/6 18:22
 * UpdateRemark:
 */
public class MatrixTestFragment extends ButtonTextFragment implements View.OnClickListener {


    @Override
    public String getClassName() {


        return "Matrix使用示例";
    }

    @Override
    protected void initEvent() {
        addTextName("Matrix的使用", this);
        addTextName("Camera的使用", this);
        addTextName("Camera与Matrix结合的使用", this);
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();

        View view = null;
        switch (tag) {
            case "Matrix的使用":
                view = new MatrixView(getActivity());
                break;
            case "Camera的使用":
                view = new CameraView(getActivity());
                break;
            case "Camera与Matrix结合的使用":
                view = new MatrixCameraView(getActivity());
                break;
        }

        addView(view);
    }
}
