package com.soul.androidcontent.customview.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.soul.androidcontent.R;
import com.soul.androidcontent.customview.CustomUI;

/**
 * Description: canvas绘制API
 * Author: zhuMing
 * CreateDate: 2020/7/2 10:09
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/2 10:09
 * UpdateRemark:
 */
public class DrawCanvasUI implements CustomUI {


    int W;
    int H;

    private final Paint mPaint;

    private final Bitmap mBitmap;

    private final Path mPath;

    private final Point mPoint;
    private Rect mRect;
    private RectF mDstRect;

    /**
     * view drawBitmap(Bitmap bitmap,float left,float top,Paint paint);在指定坐标绘制位图
     * <p>
     * void drawLine(float startX,float startY,float stopX,float stopY,Paint paint);根据给定的起始点和结束点之间绘制连线
     * <p>
     * void drawPath(Path path,Paint paint);根据给定的path，绘制连线。
     * <p>
     * void drawPoint(float X,float y,Paint paint);根据给定的坐标，绘制点。
     * <p>
     * void drawText(String text,int start,int end,Paint paint);根据给定的坐标，绘制文字
     */


    public DrawCanvasUI(Context context) {
        initWidthAndHeight(context);
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(24);
        //初始化位图
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);
        if (mBitmap != null) {
            mRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
            mDstRect = new RectF(0, 0, W, H);
        }

        //初始化path
        mPath = new Path();
        mPath.addCircle(0, 0, 30, Path.Direction.CCW);
        //初始化point
        mPoint = new Point(10, 10);
    }

    private void initWidthAndHeight(Context context) {

        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if (windowManager != null) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            W = H = (displayMetrics.widthPixels - 64);//得到矩形
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.save();
        //1、绘制位图
        canvas.translate(10, 10);
        if (mBitmap != null && mRect != null && mDstRect != null) {
            canvas.drawBitmap(mBitmap, mRect, mDstRect, mPaint);
        }
        canvas.translate(0, H + 20);
        //2、绘制线
        canvas.drawLine(0, 0, 20, 30, mPaint);
        canvas.translate(0, 30 + 20 + 30);
        //3、绘制路径
        canvas.drawPath(mPath, mPaint);
        canvas.translate(0, 30 + 10);
        //4、绘制点
        canvas.drawPoint(10, 10, mPaint);
        canvas.translate(0, 10);
        //5、绘制文字
        canvas.drawText("fdsafdsa", 0, 0, mPaint);
        canvas.restore();
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
