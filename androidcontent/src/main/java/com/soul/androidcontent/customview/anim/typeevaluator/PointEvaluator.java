package com.soul.androidcontent.customview.anim.typeevaluator;

import android.animation.TypeEvaluator;

/**
 * Description: 估值器
 * Author: zhuMing
 * CreateDate: 2020/7/9 14:46
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/9 14:46
 * UpdateRemark:
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //始末值强转为Point对象
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;

        //通过fraction计算当前动画的坐标值x,y
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());

        //返回以上述x,y组装的新的Point对象
        Point point = new Point(x, y);
        return point;
    }
}
