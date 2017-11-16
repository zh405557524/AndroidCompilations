package com.soul.customviewdemo.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.soul.library.utils.LogUtils;
import com.soul.library.utils.ScreenUtils;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.wight
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/3/20 15:39
 * <p>
 * Path             常用 API
 * |-moveTo             移动下一次操作的起点位置
 * |-setLastPoint       重置当前path中最后一个点位置，如果在绘制之前调用，效果和moveTo相同
 * |-lineTo             添加上一个点到当前点之间的直线到Path
 * |-lose               连接第一个点连接到最后一个点，形成一个闭合区域
 * |-addRect,addRoundRect,addOval,       添加(矩形，椭圆，圆，路径，圆弧)到当前
 * addCircle,addPath,addArc,arcTo       Path(注意addArc和arcTo的区别)
 * |-isEmpty        判断Path是否为空
 * |-isRect         判断Path是否是一个矩形
 * |-set            用新的路径替换到当前路径所有内容
 * |-offset         对当前路径之前的操作进行偏移(不会影响之后的操作)
 * |-quadTo,cubicTo 分别为二次和三次贝塞尔曲线的方法
 * |-rMoveTo,rLineTo,rQuadTo,rCubicTo  不带r的方法是基于原点的坐标系(偏移量),
 * rXxx方法是基于当前点坐标系(偏移量)
 * |-setFileType,getFillType,i               设置，获取判断和切换填充模式
 * isInverseFillType,toggleInverseFillType
 * |-inReserve          提示Path哈有多个点等待加入(这个方法貌似会让Path优化存储结构)
 * |-op     对两个Path进行布尔运算(即取交集，并集等操作)
 * |-computeBounds  计算Path的边界
 * |-reset,rewind  清除Path中的内容,reset不保留内部数据结构，但会保留FillType
 * rewind会保留内部的数据结构，但不保留FillTYpe
 * |-transform      矩阵变化
 */

public class PathView extends View {
    /**
     * 一个常量
     */
    private static final float C = 0.551915024494f;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 屏幕的宽
     */
    private int mScreenWidth;
    /**
     * 屏幕的高
     */
    private int mScreenHeight;
    /**
     * 贝赛尔曲线的起点
     */
    private PointF mStart;
    /**
     * 贝赛尔曲线的终点
     */
    private PointF mEnd;
    /**
     * 贝撒尔的控制点
     */
    public PointF mControl;
    /**
     * 贝撒尔的控制点
     */
    public PointF mControl1;
    /**
     * 屏幕中心的位置
     */
    int centerX, centerY;
    /**
     * 顺时针记录
     */
    private float[] mData = new float[8];
    /**
     * 顺时针记录
     */
    private float[] mCtrl = new float[16];
    /**
     * 圆的半径
     */
    private float mCircleRadius = 200;
    /**
     * 圆形的控制
     */
    private float mDifference = mCircleRadius * C;
    /**
     * 当前已进行
     */
    private int mCurrent = 0;
    /**
     * 将时长总共
     */
    private int mCount = 100;
    /**
     * 变化总时长
     */
    private int mDuration = 1000;
    /**
     * 每一份的时长
     */
    private int mPiece = mDuration / mCount;


    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化画笔
        initPaint();
        //获取屏幕的宽高
        initScreenWidthAndHeight();
        //初始化贝赛尔曲线控制点
        initBezierPoint();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //创建画笔
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);//填充模式 -描边
        mPaint.setStrokeWidth(10);//边框宽度 -10
    }

    /**
     * 获取屏幕的宽高
     */
    private void initScreenWidthAndHeight() {
        mScreenWidth = ScreenUtils.getScreenWidth();
        mScreenHeight = ScreenUtils.getScreenHeight();
    }

    /**
     * 初始化贝赛尔曲线控制点
     */
    private void initBezierPoint() {
        mStart = new PointF(0, 0);
        mEnd = new PointF(0, 0);
        mControl = new PointF(0, 0);
        mControl1 = new PointF(0, 0);


        //数据点
        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        //控制点
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        //初始化数据点和控制点的位置
        mStart.x = centerX - 200;
        mStart.y = centerY;
        mEnd.x = centerX + 200;
        mEnd.y = centerY;

        mControl.x = centerX;
        mControl.y = centerY - 100;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //        super.onDraw(canvas);

        drawCoordinateSystem(canvas);
//        canvas.translate(mScreenWidth / 2, mScreenHeight / 2);
        Path path = null;

        /**------------------------------第一组 lineTo,moveTo,setLastPoint,close--------------------------------*/

        //lineTo的用法
        //                pathLineTo(canvas);

        //moveTo的用法
        //        pathMoveTo(canvas);

        //setLastPint的用法
        //        pathLastPoint(canvas);

        //close的用法
        //        path = getClosePath();
        /**------------------------------第二组 addRect,addPath,addAct,arcTo--------------------------------*/

        //addRectPath的用法
        //        path = getAddRectPath();

        //addPath的用法
        //        path = getAddPath(canvas);

        //addAct,arcTo 的用法
        //        path = getAddArcPath(canvas);

        /**------------------------------第三组 isEmpty,isRectPath,offset--------------------------------*/

        //isEmpty 的用法
        //        path = pathIsEmpty();

        //isRect的用法 判断path是否是一个矩形，如果是一个矩形的话，将矩形信息放进参数rect中。
        //        path = getIsRectPath();

        //set 将新的path赋值到path
        //        path = getSetPath(canvas);

        //offset 的用法
        //        public void offset (float dx, float dy)
        //        public void offset (float dx, float dy, Path dst)
        //        offsetPath(canvas);

        //        canvas.drawPath(path, mPaint);

        /**------------------------------第四组 贝塞尔曲线--------------------------------*/
        //绘制贝赛尔曲线
        bezier(canvas);
        //用贝塞尔曲线绘制心
        //        heartBezier(canvas);

        /**------------------------------第五组 path一些用法--------------------------------*/
        //        rXxx
        //        rLinePath(canvas);

        //填充模式
        //        setFillType(canvas);

        //布尔操作
        //        setOp(canvas);

        //计算边界
        //        setComputeBounds(canvas);

        //重置路径 reset rewind  选择权重: FillType > 数据结构 reset>rewind


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //根据触摸位置更新控制点，并提升重绘

        mControl.x = event.getX();
        mControl.y = event.getY();
        invalidate();
        return true;

    }

    /**
     * isEmpty 的用法
     *
     * @return
     */
    @NonNull
    private Path pathIsEmpty() {
        Path path;
        path = new Path();
        LogUtils.i(path.isEmpty() + "");
        path.lineTo(100, 100);
        LogUtils.i(path.isEmpty() + "");
        return path;
    }

    /**
     * lineTo的用法
     * public void lineTo (float x, float y)
     *
     * @param canvas
     */
    private void pathLineTo(Canvas canvas) {
        Path path = new Path();
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        canvas.drawPath(path, mPaint);
    }

    /**
     * moveTo的用法
     * public void moveTo (float x, float y)
     *
     * @param canvas
     */
    private void pathMoveTo(Canvas canvas) {
        Path path = new Path();
        path.lineTo(200, 200);
        path.moveTo(200, 100);
        path.lineTo(200, 0);
        canvas.drawPath(path, mPaint);
    }

    /**
     * setLastPoint的用法
     * public void setLastPoint (float dx, float dy)
     *
     * @param canvas
     */
    private void pathLastPoint(Canvas canvas) {
        Path path = new Path();
        path.lineTo(200, 200);
        path.setLastPoint(200, 100);
        path.lineTo(200, 0);
        canvas.drawPath(path, mPaint);
    }

    /**
     * close的用法
     * public void close ()
     *
     * @return
     */
    @NonNull
    private Path getClosePath() {
        Path path = new Path();
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        path.close();
        return path;
    }

    /**
     * addRectPath的用法
     * public void addRect (float left, float top, float right, float bottom, Path.Direction dir)
     *
     * @return
     */
    @NonNull
    private Path getAddRectPath() {
        Path path;
        path = new Path();
        //CW 顺时针 CCW 逆时针
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.setLastPoint(-300, 300);//重置最后一个点的位置
        return path;
    }

    /**
     * addPath的用法
     * <p>
     * public void addPath (Path src)
     * public void addPath (Path src, float dx, float dy)
     * public void addPath (Path src, Matrix matrix)
     *
     * @param canvas
     * @return
     */
    @NonNull
    private Path getAddPath(Canvas canvas) {
        Path path;
        canvas.scale(1, -1);//翻转y坐标轴
        path = new Path();
        Path src = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);
        return path;
    }

    /**
     * addAct ,arcTo 的用法
     * <p>
     * oval:圆弧的外切矩形.startAngle:开始角度.sweepAngle:扫过角度(-360~360).forceMoveTo:是否强制使用MoveTo
     * <p>
     * addArc
     * public void addArc (RectF oval, float startAngle, float sweepAngle)
     * arcTo
     * public void arcTo (RectF oval, float startAngle, float sweepAngle)
     * public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
     *
     * @param canvas
     * @return
     */
    @NonNull
    private Path getAddArcPath(Canvas canvas) {
        Path path;
        canvas.scale(1, -1);
        path = new Path();
        path.lineTo(100, 100);
        RectF oval = new RectF(0, 0, 300, 300);
        //        path.addArc(oval, 0, 270);
        path.arcTo(oval, 0, 270, true);//等价上一句
        return path;
    }

    /**
     * isRect的用法
     * 判断path是否是一个矩形，如果是一个矩形的话，将矩形信息放进参数rect中。
     *
     * @return
     */
    @NonNull
    private Path getIsRectPath() {
        Path path;
        path = new Path();
        path.lineTo(0, 400);
        path.lineTo(400, 400);
        path.lineTo(400, 0);
        path.lineTo(0, 0);

        RectF rectF = new RectF();
        boolean b = path.isRect(rectF);
        LogUtils.i("isRect:" + b + "  rectF:" + rectF);
        return path;
    }

    /**
     * set的用法
     * 将新的path赋值到path
     *
     * @param canvas
     * @return
     */
    @NonNull
    private Path getSetPath(Canvas canvas) {
        Path path;
        canvas.scale(1, -1);

        path = new Path();//path添加一个矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path src = new Path();//src添加一个圆
        src.addCircle(0, 0, 100, Path.Direction.CW);
        path.set(src);                          //大致等于path = src
        return path;
    }

    /**
     * offset的用法
     *
     * @param canvas
     */
    private void offsetPath(Canvas canvas) {
        Path path;
        canvas.scale(1, -1);
        path = new Path();//path 中添加一个圆形
        path.addCircle(0, 0, 100, Path.Direction.CW);

        Path dst = new Path();//dst 添加一个矩形
        dst.addRect(-200, -200, 200, 200, Path.Direction.CW);

        path.offset(300, 0, dst);//平移
        //        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path, mPaint);//绘制path

        mPaint.setColor(Color.BLUE);
        canvas.drawPath(dst, mPaint);
    }

    /**
     * 贝赛尔曲线的用法
     *
     * @param canvas
     */
    private void bezier(Canvas canvas) {
        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(mStart.x, mStart.y, mPaint);
        canvas.drawPoint(mEnd.x, mEnd.y, mPaint);
        canvas.drawPoint(mControl.x, mControl.y, mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStart.x, mStart.y, mControl.x, mControl.y, mPaint);
        canvas.drawLine(mEnd.x, mEnd.y, mControl.x, mControl.y, mPaint);

        //绘制贝赛尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(mStart.x, mStart.y);
        //二阶曲线
        path.quadTo(mControl.x, mControl.y, mEnd.x, mEnd.y);
        //三阶曲线
        //        path.cubicTo(control1.x, control1.y, control2.x,control2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }


    /**
     * 用贝赛尔曲线绘制心形
     *
     * @param canvas
     */
    private void heartBezier(Canvas canvas) {
        //绘制坐标系
        drawCoordinateSystem(canvas);
        //将坐标系移动到画布中央
        canvas.translate(mScreenWidth / 2, mScreenHeight / 2);
        //将Y轴翻转
        canvas.scale(1, -1);
        //绘制辅助线
        drawAuxiliaryLine(canvas);

        //绘制贝赛尔曲线

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(mData[0], mData[1]);

        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2], mData[3]);
        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4], mData[5]);
        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0], mData[1]);

        canvas.drawPath(path, mPaint);

        mCurrent += mPiece;

        if (mCurrent < mDuration) {
            mData[1] -= 120 / mCount;
            mCtrl[7] += 80 / mCount;
            mCtrl[9] += 80 / mCount;

            mCtrl[4] -= 20 / mCount;
            mCtrl[10] += 20 / mCount;

            postInvalidateDelayed(mPiece);

        }


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
     * 绘制辅助线
     *
     * @param canvas
     */
    private void drawAuxiliaryLine(Canvas canvas) {
        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        for (int i = 0; i < 8; i += 2) {
            canvas.drawPoint(mData[i], mData[i + 1], mPaint);
        }

        for (int i = 0; i < 16; i += 2) {
            canvas.drawPoint(mCtrl[i], mCtrl[i + 1], mPaint);
        }
        //绘制辅助线
        mPaint.setStrokeWidth(4);

        for (int i = 2, j = 2; i < 8; i += 2, j += 4) {
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j + 2], mCtrl[j + 3], mPaint);
        }

        canvas.drawLine(mData[0], mData[1], mCtrl[0], mCtrl[1], mPaint);
        canvas.drawLine(mData[0], mData[1], mCtrl[14], mCtrl[15], mPaint);

    }

    /**
     * rXxx api的用法
     *
     * @param canvas
     */
    private void rLinePath(Canvas canvas) {

        //正常api
        mPaint.setColor(Color.BLUE);
        Path path = new Path();
        path.moveTo(100, 200);
        path.lineTo(200, 200);
        canvas.drawPath(path, mPaint);

        //rXxx api
        mPaint.setColor(Color.RED);
        Path rPath = new Path();
        rPath.moveTo(100, 200);
        rPath.rLineTo(200, 200);
        canvas.drawPath(rPath, mPaint);

    }

    /**
     * 奇偶规则与反奇偶规则
     */
    public void setFillType(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        //设置填充模式为奇偶规则
        //        path.setFillType(Path.FillType.EVEN_ODD);
        //反奇偶规则
        //        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        //非零缠绕规则
        path.setFillType(Path.FillType.WINDING);
        //反非零缠绕规则
        //        path.setFillType(Path.FillType.INVERSE_WINDING);
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.addRect(-400, -400, 400, 400, Path.Direction.CW);

        canvas.drawPath(path, mPaint);
    }

    /**
     * 布尔操作
     *
     * @param canvas
     */
    private void setOp(Canvas canvas) {


        //        mPaint.setStyle(Paint.Style.FILL);
        //
        //        Path path1 = new Path();
        //        Path path2 = new Path();
        //        Path path3 = new Path();
        //        Path path4 = new Path();
        //
        //        path1.addCircle(0, 0, 200, Path.Direction.CW);
        //        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        //        path3.addCircle(0, -100, 100, Path.Direction.CCW);
        //        path4.addCircle(0, 100, 100, Path.Direction.CCW);
        //        //DIFFERENCE 差集  REVERES_DIFFERENCE 反向差集
        //        //INTERSECT 交集   UNION 并集 XOR 异或
        //        path1.op(path2, Path.Op.DIFFERENCE);
        //        path1.op(path3, Path.Op.UNION);
        //        path1.op(path4, Path.Op.DIFFERENCE);
        //
        //        canvas.drawPath(path1, mPaint);
        Paint paint = new Paint();
        paint.setTextSize(24);

        int x = 80;
        int r = 100;

        canvas.translate(250, 0);

        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();

        path1.addCircle(-x, 0, r, Path.Direction.CW);
        path2.addCircle(x, 0, r, Path.Direction.CW);

        pathOpResult.op(path1, path2, Path.Op.DIFFERENCE);
        canvas.translate(0, 200);
        canvas.drawText("DIFFERENCE", 240, 0, paint);
        canvas.drawPath(pathOpResult, paint);

        pathOpResult.op(path1, path2, Path.Op.REVERSE_DIFFERENCE);
        canvas.translate(0, 300);
        canvas.drawText("REVERSE_DIFFERENCE", 240, 0, paint);
        canvas.drawPath(pathOpResult, paint);

        pathOpResult.op(path1, path2, Path.Op.INTERSECT);
        canvas.translate(0, 300);
        canvas.drawText("INTERSECT", 240, 0, paint);
        canvas.drawPath(pathOpResult, paint);

        pathOpResult.op(path1, path2, Path.Op.UNION);
        canvas.translate(0, 300);
        canvas.drawText("UNION", 240, 0, paint);
        canvas.drawPath(pathOpResult, paint);

        pathOpResult.op(path1, path2, Path.Op.XOR);
        canvas.translate(0, 300);
        canvas.drawText("XOR", 240, 0, paint);
        canvas.drawPath(pathOpResult, paint);
    }

    /**
     * 计算边界
     *
     * @param canvas
     */
    public void setComputeBounds(Canvas canvas) {
        //存放测量结果的矩形
        RectF rectF = new RectF();

        //创建Path并添加一些内容
        Path path = new Path();
        path.lineTo(100, -50);
        path.lineTo(100, 50);
        path.close();
        path.addCircle(-100, 0, 100, Path.Direction.CW);

        //测量Path
        path.computeBounds(rectF, true);

        canvas.drawPath(path, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);

    }
}
