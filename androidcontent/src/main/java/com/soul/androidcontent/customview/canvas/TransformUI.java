package com.soul.androidcontent.customview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.soul.androidcontent.customview.CustomUI;

/**
 * Description: canvas变化操作API
 * Author: zhuMing
 * CreateDate: 2020/7/2 10:03
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/2 10:03
 * UpdateRemark:
 */
public class TransformUI implements CustomUI {


    private final Paint mPaint;

    private final Rect mRect;
    private final Matrix mMatrix;

    /**
     * void translate(float dx,float dy);平移操作
     * <p>
     * void scale(float sx,float sy);缩放操作
     * <p>
     * void rotate(float degrees);旋转操作
     * <p>
     * void skew(float sx,float sy);倾斜操作
     * <p>
     * void clipXXX(...);//切割操作，参数指定区域内可以继续绘制
     * <p>
     * void clipOutXXX(...);反向切割操作，参数指定区域内部不可以绘制
     * <p>
     * void setMatrix(Matrix matrix);可通过matrix实现平移，缩放，旋转等操作。
     */


    public TransformUI(Context context) {
        // 画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        //矩形
        mRect = new Rect(0, 0, 100, 100);
        mMatrix = new Matrix();
        mMatrix.setScale(0.5f, 0.5f);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.translate(10, 10);
        canvas.drawRect(mRect, mPaint);

        //1、平移
        canvas.translate(20, 20);
        canvas.drawRect(mRect, mPaint);
        canvas.translate(0, 110);
        int save = canvas.save();
        //2、缩放
        canvas.scale(0.5f, 0.5f);
        canvas.drawRect(mRect, mPaint);
        canvas.restoreToCount(save);
        canvas.translate(0, 60);
        //3、旋转
        save = canvas.save();
        canvas.rotate(30);
        canvas.drawRect(mRect, mPaint);
        canvas.restoreToCount(save);
        canvas.translate(0, 110);
        //4、倾斜
        save = canvas.save();
        canvas.skew(0, 1);
        canvas.drawRect(mRect, mPaint);
        canvas.restoreToCount(save);
        canvas.translate(0, 110);
        //5、切割

        save = canvas.save();
        canvas.clipRect(10, 10, 110, 110);
        canvas.drawRect(mRect, mPaint);
        canvas.restoreToCount(save);
        canvas.translate(0, 110);
        //5、反向切割
        save = canvas.save();
        canvas.clipOutRect(10, 10, 110, 110);
        canvas.drawRect(mRect, mPaint);
        canvas.restoreToCount(save);
        canvas.translate(0, 110);

        //6、matrix
        save = canvas.save();
        canvas.setMatrix(mMatrix);
        canvas.drawRect(mRect, mPaint);
        canvas.restoreToCount(save);

    }

    @Override
    public int getHeight() {
        return 0;
    }


}
