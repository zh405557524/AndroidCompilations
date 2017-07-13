package com.soul.customviewdemo.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.wight
 * @作者：祝明
 * @描述：绘制文字
 * @创建时间：2017/3/15 14:46
 * <p>
 * 第一类
 * public void drawText (String text, float x, float y, Paint paint)
 * public void drawText (String text, int start, int end, float x, float y, Paint paint)
 * public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
 * public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
 * <p>
 * 第二类
 * public void drawPosText (String text, float[] pos, Paint paint)
 * public void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
 * <p>
 * 第三类
 * public void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
 * public void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
 */
public class DrawTextView extends View {

    private Paint mTextPaint;

    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1 创建画笔
        //创建画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);//设置颜色
        mTextPaint.setStyle(Paint.Style.FILL);//设置样式
        mTextPaint.setTextSize(46);//设置大小

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //        drawText(canvas);//第一种
        drawPostText(canvas);//第二种


    }



    /**
     * 文本绘制
     * 第一种方式
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        //文本(要绘制的内容)
        String str = "ABCDEFG";

        //参数分别为(文本 基线x 基线y 画笔)
        canvas.drawText(str, 200, 500, mTextPaint);
        //参数分别为(字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        canvas.drawText(str, 1, 3, 200, 500, mTextPaint);

        //字符数组
        char[] chars = str.toCharArray();
        //参数为 (字符数组 起始坐标 截取长度  基线x 基线y 画笔)
        canvas.drawText(chars, 1, 3, 200, 500, mTextPaint);
    }

    /**
     * 文本绘制
     * 第二种方式
     * @param canvas
     */
    private void drawPostText(Canvas canvas) {
        String str = "SLOOP";
//        canvas.drawPosText(str,new float[]{
//                100f,100f,//第一个字符位置
//                200f,200f,//i二个字符位置
//                300f,300f,
//                400f,400f,
//                500f,500f
//        },mTextPaint);
        char[] chars = str.toCharArray();

        canvas.drawPosText(chars,1,3,new float[]{
                100f,100f,
                200f,200f,
                300f,300f
        },mTextPaint);


    }
}
