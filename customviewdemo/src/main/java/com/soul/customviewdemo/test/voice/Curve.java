package com.soul.customviewdemo.test.voice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;

import com.soul.customviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：曲线路径
 * @作者：祝明
 * @创建时间：2017/11/14 13:54
 */

public class Curve {

    /**
     * 颜色
     */
    private int mColor;

    /**
     * 线款
     */
    private int mWidth;

    /**
     * 圆弧起点
     */
    private Point mStartPoint;

    /**
     * 圆弧终点
     */
    private Point mEndPoint;

    List<Point> mPointList = new ArrayList<>();

    public Curve(Context context, int width, int height) {
        mColor = ContextCompat.getColor(context, R.color.label_duration_played);
        mWidth = 8;
        int halfHeight = height / 2;
        mStartPoint = new Point(0, halfHeight);
        addPoint(width / 17, halfHeight - 2);
        addPoint(width / 6, halfHeight + 26);
        addPoint(width / 3, halfHeight);
        addPoint(width / 2, halfHeight - 74);
        addPoint(width - width / 3, halfHeight);
        addPoint(width - width / 6, halfHeight + 26);
        addPoint(width - width / 17, halfHeight - 2);
        addPoint(width, halfHeight);
    }

    public void addPoint(int x, int y) {
        Point point = new Point(x, y);
        mPointList.add(point);
    }

    public List<Point> getPointList() {
        return mPointList;
    }

    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(mColor);
        paint.setStrokeWidth(mWidth);
        Path path = new Path();
        if (mPointList.size() > 0) {
            path.moveTo(mStartPoint.x, mStartPoint.y);
            for (int i = 0; i + 1 < mPointList.size(); i += 2) {
                path.quadTo(mPointList.get(i).x, mPointList.get(i).y, mPointList.get(i + 1).x, mPointList.get(i + 1).y);
            }
        }
        canvas.drawPath(path, paint);
    }


}
