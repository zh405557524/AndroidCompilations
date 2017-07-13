package com.soul.customviewdemo.wight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.soul.customviewdemo.R;
import com.soul.library.utils.ScreenUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @作者：祝明
 * @描述：绘制图片
 * @创建时间：2016/12/28 10:02
 * <p>
 * Canvas 操作类型
 * 1 drawPicture
 * API :
 * |-public int getWidth()            获取宽度
 * |-public int getHeight()           获取高度
 * |-public Canvas beginRecording(int
 * width,int height) 开始录制(返回一个Canvas，在Canvas中所有的绘制都
 * 会存在Picture中)
 * |-public void endRecording()       结束录制
 * |-public void draw(Canvas canvas)  将picture中的内容绘制到Canvas中
 * <p>
 *
 * 2 drawBitmap
 * API:
 * |-public void drawBitmap();
 *
 *
 * 3
 */

public class CanvasPictureAndBitmap extends View {

    private Picture mPicture;
    private Bitmap mBitmap;
    private int mScreenWidth;
    private int mScreenHeight;

    public CanvasPictureAndBitmap(Context context) {
        this(context, null);
    }

    public CanvasPictureAndBitmap(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasPictureAndBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1 创建 Picture
        mPicture = new Picture();
        recording();
        /**--------------------------------------------------------------*/
        mBitmap = null;
        //bitmap 的 4中创建方式
        //1 从资源文件获取(drawable/mipmap/raw)
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv);
        //2 从资源文件获取(assets)
        try {
            InputStream is = context.getAssets().open("applemusic.webp");
            mBitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3 内存卡
        //        bitmap = BitmapFactory.decodeFile("/sdcard/meinv.png");
        //4 从网络获取
        //     bitmap =   BitmapFactory.decodeStream(is);

        //  is.close();
        mScreenWidth = ScreenUtils.getScreenWidth();
        mScreenHeight = ScreenUtils.getScreenHeight();

        /**--------------------------------------------------------------*/


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**------------------------------drawPicture  start--------------------------------*/
        //        drawPicture(canvas);


        /**------------------------------drawPicture  end--------------------------------*/


        /**------------------------------bitmap start--------------------------------*/
        //        drawBitmap(canvas);


        /**------------------------------bitmap end--------------------------------*/



    }

    /**
     * bitmap 绘制图片的三种方式
     *
     * @param canvas
     */
    private void drawBitmap(Canvas canvas) {
        //三种绘制方式
        //第一种
        canvas.drawBitmap(mBitmap, new Matrix(), new Paint());
        //第二种
        canvas.drawBitmap(mBitmap, 200, 500, new Paint());
        //第三种
        //将画布坐标系移动到画布中央
        canvas.translate(mScreenWidth / 2, mScreenHeight / 2);
        //        //指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0, 0, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        //        //指定图片在屏幕上显示的区域
        //
        RectF dst = new RectF(0, 0, 200, 400);
        Paint paint = new Paint();
        //        //绘制图片
        canvas.drawBitmap(mBitmap, src, dst, paint);
    }

    /**
     * picture 绘制图片的三种方式
     *
     * @param canvas
     */
    private void drawPicture(Canvas canvas) {
        //三种绘制方式
        //1 直接使用picture提供的draw方法绘制，对canvas有影响，可以操作性弱，不推荐。
        mPicture.draw(canvas);
        //2 使用Canvas提供的drawPicture方法绘制 对canvas不影响，可操作性较强，推荐
        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));
        //3 将Picture包装成为PictureDrawable,使用PictureDrawable的draw方法绘制.
        PictureDrawable drawable = new PictureDrawable(mPicture);
        //设置绘制区域---注意此处所绘制的实际内容不会缩放。
        drawable.setBounds(0, 0, 300, mPicture.getHeight());
        //绘制
        drawable.draw(canvas);

    }

    //2 录制内容方法
    private void recording() {
        //开始录制(接受)
        Canvas canvas = mPicture.beginRecording(500, 500);
        //创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        //在Canvas中具体操作
        //位移
        canvas.translate(250, 250);
        //绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();
    }


}

