package com.soul.androidcontent.customview.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.soul.androidcontent.R;
import com.soul.androidcontent.customview.CustomUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 颜色滤镜
 * Author: zhuMing
 * CreateDate: 2020/7/1 11:56
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/1 11:56
 * UpdateRemark:
 */
public class ColorFilterUI implements CustomUI {

    /**
     * 宽
     */
    int W;

    /**
     * 高
     */
    int H;

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 图像
     */
    private Bitmap mBitmap;

    /**
     * 色彩矩阵集合
     */
    private List<float[]> filters = new ArrayList<>();

    private LightingColorFilter mLightingColorFilter;

    private PorterDuffColorFilter mPorterDuffColorFilter;

    private ColorMatrixColorFilter mColorMatrixColorFilter;
    private final Rect mRectF;
    private final RectF mDstRectF;

    public ColorFilterUI(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);
        initColorColorFilter();
        initColorMatrix();

        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if (windowManager != null) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            W = H = (displayMetrics.widthPixels - 64) / 2;
        }

        mRectF = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mDstRectF = new RectF(0, 0, W, H);
    }

    /**
     * 初始化色彩滤镜
     */
    private void initColorColorFilter() {

        //滤镜 红色去除
        mLightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
        //原始效果
        //        LightingColorFilter lighting = new LightingColorFilter(0xffffff, 0x000000);
        //绿色更亮
        //        LightingColorFilter lighting = new LightingColorFilter(0xffffff, 0x003000);

        mPorterDuffColorFilter = new
                PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);

        mColorMatrixColorFilter = new ColorMatrixColorFilter(new ColorMatrix(colormatrix_heibai));

    }

    /**
     * 初始化色彩矩阵
     */
    private void initColorMatrix() {
        filters.add(colormatrix_heibai);
        filters.add(colormatrix_fugu);
        filters.add(colormatrix_gete);
        filters.add(colormatrix_chuan_tong);
        filters.add(colormatrix_danya);
        filters.add(colormatrix_guangyun);
        filters.add(colormatrix_fanse);
        filters.add(colormatrix_hepian);
        filters.add(colormatrix_huajiu);
        filters.add(colormatrix_jiao_pian);
        filters.add(colormatrix_landiao);
        filters.add(colormatrix_langman);
        filters.add(colormatrix_ruise);
        filters.add(colormatrix_menghuan);
        filters.add(colormatrix_qingning);
        filters.add(colormatrix_yese);
    }

    @Override
    public void onDraw(Canvas canvas) {


        //1 lighting滤镜
        mPaint.setColorFilter(mLightingColorFilter);
        canvas.drawBitmap(mBitmap, mRectF, mDstRectF, mPaint);
        canvas.translate(0, H + 10);

        //2 PorterDuff滤镜
        mPaint.setColorFilter(mPorterDuffColorFilter);

        canvas.drawBitmap(mBitmap, mRectF, mDstRectF, mPaint);
        canvas.translate(0, H + 10);

        //3 ColorMatrix滤镜
        mPaint.setColorFilter(mColorMatrixColorFilter);
        canvas.drawBitmap(mBitmap, mRectF, mDstRectF, mPaint);

    }

    @Override
    public int getHeight() {
        return 0;
    }


    /**
     * ------------------------------色彩矩阵 start--------------------------------
     */
    // 黑白
    public static final float colormatrix_heibai[] = {
            0.8f, 1.6f, 0.2f, 0, -163.9f,
            0.8f, 1.6f, 0.2f, 0, -163.9f,
            0.8f, 1.6f, 0.2f, 0, -163.9f,
            0, 0, 0, 1.0f, 0};
    // 怀旧
    public static final float colormatrix_huajiu[] = {
            0.2f, 0.5f, 0.1f, 0, 40.8f,
            0.2f, 0.5f, 0.1f, 0, 40.8f,
            0.2f, 0.5f, 0.1f, 0, 40.8f,
            0, 0, 0, 1, 0};
    // 哥特
    public static final float colormatrix_gete[] = {
            1.9f, -0.3f, -0.2f, 0, -87.0f,
            -0.2f, 1.7f, -0.1f, 0, -87.0f,
            -0.1f, -0.6f, 2.0f, 0, -87.0f,
            0, 0, 0, 1.0f, 0};
    // 淡雅
    public static final float colormatrix_danya[] = {
            0.6f, 0.3f, 0.1f, 0, 73.3f,
            0.2f, 0.7f, 0.1f, 0, 73.3f,
            0.2f, 0.3f, 0.4f, 0, 73.3f,
            0, 0, 0, 1.0f, 0};
    // 蓝调
    public static final float colormatrix_landiao[] = {
            2.1f, -1.4f, 0.6f, 0.0f, -71.0f,
            -0.3f, 2.0f, -0.3f, 0.0f, -71.0f,
            -1.1f, -0.2f, 2.6f, 0.0f, -71.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    // 光晕
    public static final float colormatrix_guangyun[] = {
            0.9f, 0, 0, 0, 64.9f,
            0, 0.9f, 0, 0, 64.9f,
            0, 0, 0.9f, 0, 64.9f,
            0, 0, 0, 1.0f, 0};
    // 梦幻
    public static final float colormatrix_menghuan[] = {
            0.8f, 0.3f, 0.1f, 0.0f, 46.5f,
            0.1f, 0.9f, 0.0f, 0.0f, 46.5f,
            0.1f, 0.3f, 0.7f, 0.0f, 46.5f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    // 酒红
    public static final float colormatrix_jiuhong[] = {
            1.2f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.9f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.8f, 0.0f, 0.0f,
            0, 0, 0, 1.0f, 0};
    // 胶片
    public static final float colormatrix_fanse[] = {
            -1.0f, 0.0f, 0.0f, 0.0f, 255.0f,
            0.0f, -1.0f, 0.0f, 0.0f, 255.0f,
            0.0f, 0.0f, -1.0f, 0.0f, 255.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    // 湖光掠影
    public static final float colormatrix_huguang[] = {
            0.8f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.9f, 0.0f, 0.0f,
            0, 0, 0, 1.0f, 0};
    // 褐片
    public static final float colormatrix_hepian[] = {
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.8f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.8f, 0.0f, 0.0f,
            0, 0, 0, 1.0f, 0};
    // 复古
    public static final float colormatrix_fugu[] = {
            0.9f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.8f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.5f, 0.0f, 0.0f,
            0, 0, 0, 1.0f, 0};
    // 泛黄
    public static final float colormatrix_huan_huang[] = {
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.5f, 0.0f, 0.0f,
            0, 0, 0, 1.0f, 0};
    // 传统
    public static final float colormatrix_chuan_tong[] = {
            1.0f, 0.0f, 0.0f, 0, -10f,
            0.0f, 1.0f, 0.0f, 0, -10f,
            0.0f, 0.0f, 1.0f, 0, -10f,
            0, 0, 0, 1, 0};
    // 胶片2
    public static final float colormatrix_jiao_pian[] = {
            0.71f, 0.2f, 0.0f, 0.0f, 60.0f,
            0.0f, 0.94f, 0.0f, 0.0f, 60.0f,
            0.0f, 0.0f, 0.62f, 0.0f, 60.0f,
            0, 0, 0, 1.0f, 0};

    // 锐色
    public static final float colormatrix_ruise[] = {
            4.8f, -1.0f, -0.1f, 0, -388.4f,
            -0.5f, 4.4f, -0.1f, 0, -388.4f,
            -0.5f, -1.0f, 5.2f, 0, -388.4f,
            0, 0, 0, 1.0f, 0};
    // 清宁
    public static final float colormatrix_qingning[] = {
            0.9f, 0, 0, 0, 0,
            0, 1.1f, 0, 0, 0,
            0, 0, 0.9f, 0, 0,
            0, 0, 0, 1.0f, 0};
    // 浪漫
    public static final float colormatrix_langman[] = {
            0.9f, 0, 0, 0, 63.0f,
            0, 0.9f, 0, 0, 63.0f,
            0, 0, 0.9f, 0, 63.0f,
            0, 0, 0, 1.0f, 0};
    // 夜色
    public static final float colormatrix_yese[] = {
            1.0f, 0.0f, 0.0f, 0.0f, -66.6f,
            0.0f, 1.1f, 0.0f, 0.0f, -66.6f,
            0.0f, 0.0f, 1.0f, 0.0f, -66.6f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f};


    /**------------------------------色彩矩阵 end--------------------------------*/


}
