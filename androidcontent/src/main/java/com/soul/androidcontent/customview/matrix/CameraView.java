package com.soul.androidcontent.customview.matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.soul.androidcontent.customview.matrix.api.BaseView;
import com.soul.androidcontent.customview.matrix.api.CameraTranslate;


/**
 * Description:
 * <p>
 * * 一 基本方法 save ,restore 保存，回滚
 * * 二 常用方法 getMatrix,applyToCanvas 获取Matrix,应用到画布
 * * 三 translate 位移
 * * 四 rotat rotateX ,rotateY,rotateZ 各种旋转
 * * 五 setLocation,getLocationX getLocationY ,getLocationZ设置与获取相机的位置
 * <p>
 * Author: 祝明
 * CreateDate: 2019/4/23 下午2:07
 * UpdateUser:
 * UpdateDate: 2019/4/23 下午2:07
 * UpdateRemark:
 */
public class CameraView extends View {


    private BaseView mCamera;
    private float mDownX;
    private float mDownY;

    public CameraView(Context context) {
        this(context, null);
    }

    public CameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        //平移
        mCamera = new CameraTranslate(context);
        //        mCamera = new CameraRotate(context);


    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        mCamera.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                final float moveX = event.getX();
                final float moveY = event.getY();
                mCamera.move(moveX - mDownX, moveY - mDownY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP://松手
                final float upX = event.getX();
                final float upY = event.getY();
                mCamera.move(0, 0);
                invalidate();
                break;
        }
        return true;
    }
}
