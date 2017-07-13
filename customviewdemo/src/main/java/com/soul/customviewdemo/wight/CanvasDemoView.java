package com.soul.customviewdemo.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/12/5 9:34
 */

public class CanvasDemoView extends View {

    private Paint mPaint;

    public CanvasDemoView(Context context) {
        this(context, null);
    }

    public CanvasDemoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);

        /**------------------------------绘制圆弧start--------------------------------*/


        //        RectF rectF = new RectF(100, 100, 400, 300);
        //        //绘制背景矩形
        //        mPaint.setColor(Color.GRAY);
        //        canvas.drawRect(rectF,mPaint);
        //
        //        //绘制圆弧
        //        mPaint.setColor(Color.BLUE);
        //        canvas.drawArc(rectF,0,90,false,mPaint);
        //
        //        RectF rectF1 = new RectF(100, 400, 400, 600);
        //        mPaint.setColor(Color.GRAY);
        //        canvas.drawRect(rectF1,mPaint);
        //
        //        mPaint.setColor(Color.BLUE);
        //        canvas.drawArc(rectF1,0,90,true,mPaint);
        /**------------------------------绘制圆弧end--------------------------------*/

        /**------------------------------Paint的三种模式 start--------------------------------*/
        //        Paint paint = new Paint();
        //        paint.setColor(Color.BLUE);
        //        paint.setStrokeWidth(40);//为了实验效果明显，特地设置描边宽度非常大
        //
        //        //描边
        //        paint.setStyle(Paint.Style.STROKE);
        //         paint.setStrokeWidth(40);
        //        canvas.drawCircle(100, 100, 50, paint);
        //        //填充
        //        paint.setStyle(Paint.Style.FILL);
        //        canvas.drawCircle(100, 300, 50, paint);
        //
        //        //描边加填充
        //        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //        canvas.drawCircle(100, 500, 50, paint);

        /**------------------------------Paint的三种模式 end--------------------------------*/

        /**-----------------------------位移 start--------------------------------*/
        //        //在坐标原点绘制一个黑色圆形
        //        mPaint.setColor(Color.BLACK);
        //        canvas.translate(200, 200);
        //        canvas.drawCircle(0, 0, 100, mPaint);
        //
        //        //在坐标原点绘制一个蓝色圆形
        //        mPaint.setColor(Color.BLUE);
        //        canvas.translate(200, 200);
        //        canvas.drawCircle(0, 0, 100, mPaint);

        /**------------------------------位移 end--------------------------------*/


        /**------------------------------缩放 start--------------------------------*/

        //        canvas.translate(getWidth() / 2, getHeight() / 2);
        //
        //        RectF rectF = new RectF(0, -400, 400, 0);
        //
        //        mPaint.setColor(Color.BLACK);
        //
        //        canvas.drawRect(rectF, mPaint);
        //        canvas.scale(0.5f, 0.5f);//画布缩放
        //        canvas.scale(-0.5f, -0.5f);//画布缩放
        //        canvas.scale(0.5f, 0.5f,200,0);// 画布缩放  <-- 缩放中心向右偏移了200个单位
        //        canvas.scale(-0.5f, -0.5f, 200, 0);// 画布缩放  <-- 缩放中心向右偏移了200个单位

        //        mPaint.setColor(Color.BLUE);//回执蓝色矩形
        //        canvas.drawRect(rectF, mPaint);

        /*----------------------------------------------*/
        //        canvas.translate(getWidth() / 2, getHeight() / 2);
        //        RectF rectF = new RectF(-400, -400, 400, 400);
        //        mPaint.setColor(Color.BLUE);
        //        mPaint.setStyle(Paint.Style.STROKE);
        //        for (int i = 0; i <= 20; i++) {
        //            canvas.scale(0.9f, 0.9f);
        //            canvas.drawRect(rectF, mPaint);
        //        }
        /**------------------------------缩放 end--------------------------------*/


        /**------------------------------旋转 start--------------------------------*/
        RectF rectF = new RectF(0, -400, 400, 0);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);
        canvas.rotate(180, 200, 0);//旋转180度 旋转中心向右偏移200
        canvas.rotate(180);//
        canvas.rotate(20);//
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.drawCircle(0, 0, 400, mPaint);
        canvas.drawCircle(0, 0, 380, mPaint);
        for (int i = 0; i <= 360; i += 10) {
            canvas.drawLine(0, 380, 0, 400, mPaint);
            canvas.rotate(10);
        }

        //        canvas.save();

        /**------------------------------旋转 end--------------------------------*/

        /**------------------------------错切 start--------------------------------*/
        RectF rectF1 = new RectF(0, 0, 200, 200);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF1, mPaint);

        canvas.skew(1, 0);//水平错切  <- 45度
        canvas.skew(0, 1);//垂直错切

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF1, mPaint);

        /**------------------------------错切 end--------------------------------*/
        //        canvas.restore();

    }
}
