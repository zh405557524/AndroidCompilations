package com.soul.androidcompilptions.customview.customImageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.customview.customImageview
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/12/6 10:11
 */

public class PieView extends View {

    /**
     * 画笔
     */
    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;
    private List<PieData> mData;
    /**
     * 颜色表(注意:此处定义颜色使用的是ARGB，带Alpha通道)
     */
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private int mStartAngle = 0;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData) {
            return;
        }
        int currentStartAngle = mStartAngle;        //当前起始角度
        //画布移位
        canvas.translate(mWidth / 2, mHeight / 2);       //当前原点中心
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);//饼状图半径
        RectF rect = new RectF(-r, -r, r, r);

        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rect, currentStartAngle, pie.getAngle(), true, mPaint);
            currentStartAngle += pie.getAngle();
        }
    }

    //设置数据
    public void setData(List<PieData> data) {
        mData = data;
        initData(data);
        invalidate();
    }

    /**
     * 设置起始角度
     */
    public void setStartAngle(int startAngle) {
        mStartAngle = startAngle;
        invalidate();
    }

    //初始化数据
    private void initData(List<PieData> data) {
        if (null == data || data.size() == 0) {//数据有问题直接返回
            return;
        }

        float sumValue = 0;
        for (int i = 0; i < data.size(); i++) {
            PieData pie = data.get(i);
            sumValue += pie.getValue();//计算数值和

            int j = i % mColors.length;//设置颜色
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < data.size(); i++) {
            PieData pie = data.get(i);

            float percentage = pie.getValue() / sumValue;//百分比
            float angle = percentage * 360;

            pie.setPercentage(percentage);
            pie.setAngle(angle);
            sumAngle += angle;
        }

    }


}




