package com.soul.androidcontent.customview.matrix.api;

import android.graphics.Canvas;

/**
 * Description:
 * Author: 祝明
 * CreateDate: 2019/4/23 下午1:22
 * UpdateUser:
 * UpdateDate: 2019/4/23 下午1:22
 * UpdateRemark:
 */
public interface BaseView {

    /**
     * 绘制
     *
     * @param canvas
     */
    void onDraw(Canvas canvas);

    void move(float vX, float vY);

}
