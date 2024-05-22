package com.soul.androidcontent.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Description:多阶贝塞尔曲线
 * Author: 祝明
 * CreateDate: 2019/4/21 下午5:45
 * UpdateUser:
 * UpdateDate: 2019/4/21 下午5:45
 * UpdateRemark:
 */
public class BezierView extends View {

    private Paint mPaint, mLinePointPaint;
    private Path mPath;


    private ArrayList<PointF> mControlPoints; //控制点集，没有分数据点还是控制点

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        mLinePointPaint = new Paint();
        mLinePointPaint.setAntiAlias(true);
        mLinePointPaint.setStrokeWidth(4);
        mLinePointPaint.setStyle(Paint.Style.STROKE);
        mLinePointPaint.setColor(Color.GRAY);

        mPath = new Path();
        mControlPoints = new ArrayList<>();
        initPoints();
    }

    private void initPoints() {
        mControlPoints.clear();
        final Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int x = random.nextInt(400) + 200;
            int y = random.nextInt(800) + 200;
            final PointF pointF = new PointF(x, y);
            mControlPoints.add(pointF);
        }
        buildBezierPoints();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final long l = System.currentTimeMillis();
        int size = mControlPoints.size();
        PointF point;
        for (int i = 0; i < size; i++) {
            point = mControlPoints.get(i);
            if (i > 0) {
                //控制点连线
                canvas.drawLine(mControlPoints.get(i - 1).x, mControlPoints.get(i - 1).y,
                        point.x, point.y, mLinePointPaint);
            }
            //控制点
            canvas.drawCircle(point.x, point.y, 12, mLinePointPaint);
        }

        canvas.drawPath(mPath, mPaint);
        Log.i("Tag", "time:" + (System.currentTimeMillis() - l));
    }

    private ArrayList<PointF> buildBezierPoints() {
        mPath.reset();
        ArrayList<PointF> points = new ArrayList<>();
        int order = mControlPoints.size() - 1;//阶数
        //画的密集度，帧
        float delta = 1.0f / 100;
        for (float t = 0; t <= 1; t += delta) {
            PointF pointF = new PointF(deCasteJauX(order, 0, t), deCasteJauY(order, 0, t));
            points.add(pointF);
            if (points.size() == 1) {
                mPath.moveTo(points.get(0).x, points.get(0).y);
            } else {
                mPath.lineTo(pointF.x, pointF.y);
            }

        }

        return points;
    }


    /***
     *   decasteljau算法
     *   p(i,j) = (1-t)*p(i-1,j)+t*p(i-1,j+1)
     * @param i 阶数
     * @param j  控制点
     * @param t  时间
     * @return
     */
    private float deCasteJauX(int i, int j, float t) {
        if (i == 1) {
            return (1 - t) * mControlPoints.get(j).x + t * mControlPoints.get(j + 1).x;
        }
        return (1 - t) * deCasteJauX(i - 1, j, t) + t * deCasteJauX(i - 1, j + 1, t);
    }

    /***
     *   decasteljau算法
     *   p(i,j) = (1-t)*p(i-1,j)+t*p(i-1,j+1)
     * @param i 阶数
     * @param j  控制点
     * @param t  时间
     * @return
     */
    private float deCasteJauY(int i, int j, float t) {
        if (i == 1) {
            return (1 - t) * mControlPoints.get(j).y + t * mControlPoints.get(j + 1).y;
        }
        return (1 - t) * deCasteJauY(i - 1, j, t) + t * deCasteJauY(i - 1, j + 1, t);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            init();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}






























