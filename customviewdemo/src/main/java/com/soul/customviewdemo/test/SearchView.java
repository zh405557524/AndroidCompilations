package com.soul.customviewdemo.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.test
 * @作者：祝明
 * @描述：pathMeasure练习-->搜索框
 * @创建时间：2017/3/27 17:45
 */

public class SearchView extends View {
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 搜索的路径
     */
    private Path mSearchPath;
    /**
     * 圆的路径
     */
    private Path mCirclePath;
    private int mViewWidth;
    private int mViewHeight;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAll();
    }

    private void initAll() {
        initPaint();
        initPath();
        initListener();
        initHandler();
        initAnimal();
    }


    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);

    }

    /**
     * 初始化路径
     */
    private void initPath() {
        //搜索
        mSearchPath = new Path();
        mSearchPath.addCircle(0, 0, 50, Path.Direction.CW);
        PathMeasure searchPathMeasure = new PathMeasure(mSearchPath, true);
        float searchLength = searchPathMeasure.getLength();

        //        mSearchPath.moveTo();

        //圆
        mCirclePath = new Path();

    }


    /**
     * 初始化动画
     */
    private void initAnimal() {


    }

    /**
     * 监听
     */
    private void initListener() {


    }

    /**
     * 初始化handler
     */
    private void initHandler() {


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        searchCanvas(canvas);
    }

    /**
     * 绘制搜索和圆
     *
     * @param canvas
     */
    private void searchCanvas(Canvas canvas) {

        //搜索
        canvas.drawPath(mSearchPath, mPaint);

        //圆
        canvas.drawPath(mCirclePath, mPaint);



    }





}
