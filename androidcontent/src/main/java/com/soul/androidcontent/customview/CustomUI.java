package com.soul.androidcontent.customview;

import android.graphics.Canvas;

/**
 * Description: TODO
 * Author: zhuMing
 * CreateDate: 2020/6/29 17:56
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/29 17:56
 * UpdateRemark:
 */
public interface CustomUI {
    void onDraw(Canvas canvas);

    int getHeight();
}
