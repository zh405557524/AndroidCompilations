package com.soul.customviewdemo.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @描述：View绘制的工具类
 * @作者：祝明
 * @项目名:AndroidCompilations
 * @创建时间：2018/6/22 16:23
 */

public class ViewSubUtils {


    /**
     * 将画布移动到布局中间
     *
     * @param canvas 画笔
     * @param width  画布宽
     * @param height 画布高
     */
    public static void moveCenter(Canvas canvas, int width, int height) {
        canvas.translate(width / 2, height / 2);
    }

    /**
     * 坐标辅助线
     *
     * @param canvas 画笔
     */
    public static void CoordinateAuxiliaryLine(Canvas canvas) {

        Paint fuZhuPaint = new Paint();
        fuZhuPaint.setColor(Color.RED);
        fuZhuPaint.setStrokeWidth(5);
        fuZhuPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -2000, 0, 2000, fuZhuPaint);
        canvas.drawLine(-2000, 0, 2000, 0, fuZhuPaint);

        canvas.restore();
    }
}
