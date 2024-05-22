package com.soul.androidcontent.customview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Description:状态保存和恢复
 * Author: 祝明
 * CreateDate: 2019/4/19 下午2:23
 * UpdateUser:
 * UpdateDate: 2019/4/19 下午2:23
 * UpdateRemark:
 */
public class SaveRestoreView extends View {

    private Paint mPaint;

    public SaveRestoreView(Context context) {
        this(context, null);
    }

    public SaveRestoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SaveRestoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("Tag", "saveCount0:" + canvas.getSaveCount());
        //        canvas.drawRect(100, 100, 350, 350, mPaint);
        //
        //        Log.i("Tag", "saveCount1:" + canvas.getSaveCount());
        //        final int save = canvas.save();
        //
        //        canvas.translate(50, 50);
        //        mPaint.setColor(Color.GREEN);
        //        canvas.drawRect(0, 0, 250, 250, mPaint);
        //
        //        canvas.save();
        //        canvas.translate(50, 50);
        //        mPaint.setColor(Color.GRAY);
        //        canvas.drawRect(0, 0, 250, 250, mPaint);
        //
        //        final int saveCount = canvas.getSaveCount();
        //        Log.i("Tag", "saveCount2:" + saveCount);
        //
        //        //        canvas.restore();
        //        Log.i("Tag", "saveCount3:" + canvas.getSaveCount());
        //
        //        canvas.restoreToCount(save);
        //        //        canvas.restore();
        //        Log.i("Tag", "saveCount4:" + canvas.getSaveCount());
        //
        //        canvas.drawLine(0, 0, 200, 250, mPaint);

        canvas.drawRect(100, 100, 350, 350, mPaint);

        final int layerId = canvas.saveLayer(0, 0, 350, 350, mPaint);

        mPaint.setColor(Color.RED);
        final Matrix matrix = new Matrix();
        matrix.setTranslate(50, 50);
        canvas.setMatrix(matrix);
        canvas.drawRect(0, 0, 350, 350, mPaint);//由于平移操作，导致绘制的矩形超出图层的大小，所以绘制不完全
        canvas.restoreToCount(layerId);

        mPaint.setColor(Color.GREEN);

        canvas.drawRect(0, 0, 100, 100, mPaint);
    }


}
