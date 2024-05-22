package com.soul.androidcontent.customview.paint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;

import com.soul.androidcontent.R;
import com.soul.androidcontent.customview.CustomUI;
import com.soul.lib.Global;
import com.soul.lib.utils.LogUtils;


/**
 * Description: Paint API 集合
 * Author: zhuMing
 * CreateDate: 2020/6/29 17:57
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/29 17:57
 * UpdateRemark:
 */
public class GradientPaintUI implements CustomUI {


    /**
     *   setColor(Color.RED);//设置颜色
     *   setARGB(255, 255, 255, 0);//设置paint对象颜色，范围0~255
     *   setAlpha(200);//设置alpha 不透明，范围0~255
     *   setAntiAlias(true);//抗锯齿
     *   setStyle(Paint.Style.STROKE);//描边效果 FILL 填充;STROKE 描边; FILL_AND_STROKE 填充并表变
     *   setStrokeWidth(4);//描边宽度
     *   setStrokeCap(Paint.Cap.ROUND);//圆角效果 BUTT 默认; ROUND 圆角;SQUARE 方形
     *   setStrokeJoin(Paint.Join.MITER);//拐角风格 MITER 尖角;ROUND 切除尖角;BEVEL 圆角
     *   setShader(new SweepGradient(200, 200, Color.BLUE, Color.RED));//设置环形渲染器
     */

    /**
     * 画笔
     */
    private final Paint mPaint;

    private final Bitmap mBitmap;
    /**
     * 线性渲染
     */
    private final LinearGradient mLinearGradient;

    /**
     * 环形渲染
     */
    private final RadialGradient mRadialGradient;

    /***
     * 扫描渲染
     */
    private final SweepGradient mSweepGradient;

    /**
     * 位图渲染
     */
    private final BitmapShader mBitmapShader;

    /**
     * 组合渲染
     */
    private final ComposeShader mComposeShader;
    private final Paint mPaintShader;

    public GradientPaintUI() {
        //获取bitmap
        mBitmap = BitmapFactory.decodeResource(Global.getResources(), R.drawable.girl);
        //创建画笔
        mPaint = new Paint();
        //1、设置颜色
        mPaint.setColor(Color.RED);
        //2、设置透明度
        mPaint.setAlpha(200);
        //3、设置抗锯齿
        mPaint.setAntiAlias(true);
        //4.1、设置描边效果，为fill填充
        mPaint.setStyle(Paint.Style.FILL);
        //5、设置描边宽度为4
        mPaint.setStrokeWidth(10);


        mPaintShader = new Paint();
        mPaintShader.setColor(Color.BLUE);
        //6.1、线性渲染
        mLinearGradient = new LinearGradient(0, 0, 250, 0, new int[]{Color.RED, Color.BLUE, Color.GREEN},
                new float[]{0.1f, 0.5f, 1f}, Shader.TileMode.CLAMP);
        //6.2 环形渲染
        mRadialGradient = new RadialGradient(0, 0, 100, new int[]{Color.GREEN, Color.YELLOW, Color.RED},
                null, Shader.TileMode.CLAMP);

        //6.3 扫描渲染
        mSweepGradient = new SweepGradient(250, 250, Color.RED, Color.GREEN);

        //6.4 位图渲染
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //        mShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        //        mShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        //6.5 组合渲染
        BitmapShader bitmapShaderCompose = new BitmapShader(mBitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        LinearGradient linearGradientCompose = new LinearGradient(0, 0, 1000, 16000, new
                int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.CLAMP);
        mComposeShader = new ComposeShader(bitmapShaderCompose, linearGradientCompose, PorterDuff.Mode.MULTIPLY);

    }


    @Override
    public void onDraw(Canvas canvas) {
        LogUtils.i("Tag", "onDraw");
        canvas.save();
        canvas.translate(100, 100);
        canvas.drawCircle(0, 0, 20, mPaint);
        canvas.translate(0, 100);

        //4.2、设置描边效果，为STROKE描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, 20, mPaint);
        canvas.translate(0, 100);

        //4.3、设置描边效果，为FILL_AND_STROKE 填充并表变
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(0, 0, 20, mPaint);
        canvas.translate(100, 0);

        canvas.restore();
        //6.1、线性渲染
        canvas.translate(200, 100);
        mPaintShader.setShader(mLinearGradient);
        RectF rectF = new RectF(0, 0, 250, 100);
        canvas.drawRect(rectF, mPaintShader);


        //6.2 环形渲染
        canvas.translate(100, 200);
        mPaintShader.setShader(mRadialGradient);
        mPaintShader.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 100, mPaintShader);
        canvas.translate(0, 200);

        //6.3 扫描渲染
        mPaintShader.setShader(mSweepGradient);
        canvas.drawRect(rectF, mPaintShader);
        canvas.translate(0, 200);

        //6.4 位图渲染
        mPaintShader.setShader(mBitmapShader);
        mPaintShader.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 100, mPaintShader);
        canvas.translate(0, 200);

        //6.5 组合渲染
        mPaintShader.setShader(mComposeShader);
        mPaintShader.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 100, mPaintShader);
        canvas.translate(0, 200);

        canvas.restore();
    }

    @Override
    public int getHeight() {
        return 0;
    }

}

