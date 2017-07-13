package com.soul.customviewdemo.wight;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.soul.customviewdemo.R;
import com.soul.library.utils.LogUtils;
import com.soul.library.utils.ScreenUtils;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.wight
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/3/27 11:38
 * <p>
 * PathMeasure  常用API
 * <p>
 * |-setPath(Path path,boolean forceClosed) 关联一个Path
 * |-isClosed 是否闭合
 * |-getLength 获取Path的长度
 * |-nextContour 跳转到下一个轮廓
 * |-getSegment(float startD,float stopD,Path dst,boolean startWithMoveTo) 截取片段
 * |-getPostTan(float distance,float[] pos,float[] tan) 获取指定长度的位置坐标及该点切线值
 * |-getMatrix(float distance,Matrix matrix,int flags) 获取指定长度位置坐标及该点Matrix
 */

public class PathMeasureView extends View {

    /**
     * 屏幕的宽
     */
    private int mScreenWidth;
    /**
     * 屏幕的高
     */
    private int mScreenHeight;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前的位置
     */
    private float mCurrentValue;
    /**
     * 点的位置
     */
    private float[] pos;

    /**
     * 点的正切位置
     */
    private float[] tan;
    /**
     * 资源类的对象
     */
    private Resources mRes;
    /**
     * 箭头的图标
     */
    private Bitmap mArrowsBitmap;

    private Matrix mMatrix;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRes = context.getResources();

        //获取屏幕的宽高
        initScreenWidthAndHeight();

        //初始化画笔
        initPaint();

        //初始化tan用法的图片
        initTan();


    }

    private void initTan() {

        //获取options
        BitmapFactory.Options options = new BitmapFactory.Options();
        //缩放比例为2
        options.inSampleSize = 4;
        mArrowsBitmap = BitmapFactory.decodeResource(mRes, R.drawable.arrows, options);
        pos = new float[2];
        tan = new float[2];
        mMatrix = new Matrix();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
    }

    /**
     * 获取屏幕的宽高
     */
    private void initScreenWidthAndHeight() {
        mScreenWidth = ScreenUtils.getScreenWidth();
        mScreenHeight = ScreenUtils.getScreenHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateSystem(canvas);
        canvas.translate(mScreenWidth / 2, mScreenHeight / 2);
        //setPath
        //        setPath(canvas);

        //getSegment 截取片段的 用法
        //        getSegment(canvas);

        //nextContour 的用法
        //        nextContour(canvas);

        //getPostTan 获取切线点的位置
        getPostTan(canvas);


    }


    /**
     * setPath 的用法
     *
     * @param canvas
     */
    private void setPath(Canvas canvas) {
        //forceCosed 的设置不影响path，影响测量结构
        Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);

        PathMeasure pathMeasure1 = new PathMeasure(path, false);
        PathMeasure pathMeasure2 = new PathMeasure(path, true);

        LogUtils.i("pathMeasure-->false:" + pathMeasure1.getLength());
        LogUtils.i("pathMeasure-->true:" + pathMeasure2.getLength());

        canvas.drawPath(path, mPaint);
    }

    /**
     * 绘制坐标
     *
     * @param canvas
     */
    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();//绘制坐标系

        //将坐标系移动到画布中央
        canvas.translate(mScreenWidth / 2, mScreenHeight / 2);
        //将Y轴翻转
        canvas.scale(1, -1);

        Paint fuZhuPaint = new Paint();
        fuZhuPaint.setColor(Color.RED);
        fuZhuPaint.setStrokeWidth(5);
        fuZhuPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -2000, 0, 2000, fuZhuPaint);
        canvas.drawLine(-2000, 0, 2000, 0, fuZhuPaint);

        canvas.restore();
    }


    /**
     * getSegment 截取片段的用法
     *
     * @param canvas
     */
    private void getSegment(Canvas canvas) {

        //创建一个path
        Path path = new Path();
        //添加一个矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        //创建一个用于存储截取片、片段的path
        Path dst = new Path();
        dst.lineTo(-100, -100);
        //创建PathMeasure
        PathMeasure pathMeasure = new PathMeasure(path, false);
        //开始截取  true 保证截取到的path不会发生形变，false保证存储截取片段的Path的连续性
        pathMeasure.getSegment(200, 600, dst, true);
        //将截取的片段绘制出来
        canvas.drawPath(dst, mPaint);

    }

    /**
     * nextContour的 用法
     * 转跳到下一条曲线上
     *
     * @param canvas
     */
    private void nextContour(Canvas canvas) {

        //创建一个path
        Path path = new Path();
        //添加两个rect 一大一小
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        //创建pathMeasure
        PathMeasure pathMeasure = new PathMeasure(path, true);
        //获取第一条长度
        float length1 = pathMeasure.getLength();
        //nextContour 转到下一条
        pathMeasure.nextContour();
        //获取长度
        float length2 = pathMeasure.getLength();

        LogUtils.i("length1:" + length1 + "------ length2:" + length2);
        //绘制
        canvas.drawPath(path, mPaint);

    }

    /**
     * 获取切线点的位置
     *
     * @param canvas
     */
    private void getPostTan(Canvas canvas) {

        //画一个圆
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);

        mCurrentValue += 0.001f;
        if (mCurrentValue >= 1) {
            mCurrentValue = 0.f;
        }
        //获取正切点
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        pathMeasure.getPosTan(length * mCurrentValue, pos, tan);

        //计算图片放置的位置
        mMatrix.reset();

        //计算图片选择角度
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        //        //旋转图片
        //        mMatrix.postRotate(degrees, mArrowsBitmap.getWidth() / 2, mArrowsBitmap.getHeight() / 2);
        // 获取当前位置的坐标以及趋势的矩阵

        //将图片点与中心点重合
        //        mMatrix.postTranslate(pos[0] - mArrowsBitmap.getHeight() / 2, pos[1] -
        //                mArrowsBitmap.getHeight() / 2);

        pathMeasure.getMatrix(pathMeasure.getLength() * mCurrentValue, mMatrix,
                PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-mArrowsBitmap.getWidth() / 2, -mArrowsBitmap.getHeight() / 2);
        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mArrowsBitmap, mMatrix, mPaint);

        invalidate();

    }


}
