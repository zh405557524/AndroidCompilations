package com.soul.androidcontent.customview.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.soul.androidcontent.R;

/**
 * Description:path测量的用法
 * Author: 祝明
 * CreateDate: 2019/4/22 下午5:31
 * UpdateUser:
 * UpdateDate: 2019/4/22 下午5:31
 * UpdateRemark:
 */
public class PathMeasureView extends View {

    private Paint mPaint = new Paint();
    private Paint mLinePaint = new Paint();//坐标系

    private Bitmap mBitmap;


    private Matrix mMatrix = new Matrix();
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    private Path mPath = new Path();

    private float mFloat;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow, options);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mLinePaint);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mLinePaint);
        canvas.translate(getWidth() / 2, getHeight() / 2);

        // 1 setPath
        //        final Path path = new Path();
        //        path.lineTo(0, 200);
        //        path.lineTo(200, 200);
        //        path.lineTo(200, 0);
        //
        //
        //        /**
        //         * pathMeasure 需要关联一个创建好的path，forceClosed会影响path的测量结果
        //         */
        //        PathMeasure pathMeasure = new PathMeasure();
        //        pathMeasure.setPath(path, true);
        //
        //        Log.i("Tag", "onDraw:" + pathMeasure.getLength());
        //
        //        final PathMeasure pathMeasure1 = new PathMeasure(path, false);
        //
        //        Log.i("Tag", "onDraw1:" + pathMeasure1.getLength());
        //
        //        path.lineTo(200, -200);
        //        pathMeasure1.setPath(path, false);
        //        Log.i("Tag", "onDraw2:" + pathMeasure1.getLength());
        //        //如果path 进行了调整，需要重新调用setPath 进行调整
        //        pathMeasure1.setPath(path, false);
        //        Log.i("Tag", "onDraw3:" + pathMeasure1.getLength());


        //2  getSegment()
        //        final Path path = new Path();
        //        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        //        final Path dst = new Path();
        //        dst.lineTo(-300, -300);//添加一条直线
        //        final PathMeasure pathMeasure = new PathMeasure(path, false);
        //        //截取一部分存入dst中，并且使用moveTO 保持截取得到的path第一个点位置不变。
        //        pathMeasure.getSegment(200, 1000, dst, false);
        //
        //
        //        canvas.drawPath(path, mPaint);
        //        canvas.drawPath(dst, mLinePaint);

        // 3 nextContour

        //        final Path path = new Path();
        //        path.addRect(-100, -100, 100, 100, Path.Direction.CW);//添加一个矩形
        //        path.addOval(-200, -200, 200, 200, Path.Direction.CW);
        //        canvas.drawPath(path, mPaint);
        //
        //        final PathMeasure pathMeasure = new PathMeasure(path, false);
        //        Log.i("Tag", "onDraw:" + pathMeasure.getLength());
        //        //跳转到下一跳曲线
        //        pathMeasure.nextContour();
        //        Log.i("Tag", "onDraw1:" + pathMeasure.getLength());


        //getPostTan 获取切线点的位置
        getPostTan(canvas);

    }

    /**
     * 当前的位置
     */
    private float mCurrentValue;


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


        canvas.drawPath(path, mPaint);
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
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        invalidate();

    }


}



































