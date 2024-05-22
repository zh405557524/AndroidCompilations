package com.soul.androidcontent.customview.matrix.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.soul.androidcontent.R;


/**
 * Description: 平移
 * Author: 祝明
 * CreateDate: 2019/4/23 上午11:44
 * UpdateUser:
 * UpdateDate: 2019/4/23 上午11:44
 * UpdateRemark:
 */
public class CameraTranslate implements BaseView {


    private final Bitmap mBitmap;
    private Paint mPaint;
    private final Camera mCamera;
    private final Rect mSrc;
    private final Rect mDst;

    private float moveX;
    private float moveY;

    public CameraTranslate(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv);
        mPaint = new Paint();
        mCamera = new Camera();
        mSrc = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mDst = new Rect(0, 0, 400, 300);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //x 轴平移
        mCamera.save();
        mCamera.translate(moveX, -moveY, 0);
        mCamera.applyToCanvas(canvas);
        canvas.drawBitmap(mBitmap, mSrc, mDst, mPaint);
        mCamera.restore();
    }


    public void move(float vX, float vY) {
        moveX = vX;
        moveY = vY;
    }

}
