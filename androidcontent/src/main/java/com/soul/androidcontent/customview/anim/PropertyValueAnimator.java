package com.soul.androidcontent.customview.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.soul.androidcontent.customview.anim.typeevaluator.Point;
import com.soul.androidcontent.customview.anim.typeevaluator.PointEvaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 属性动画-ValueAnimator演示
 * Author: zhuMing
 * CreateDate: 2020/7/9 10:38
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/9 10:38
 * UpdateRemark:
 */
public class PropertyValueAnimator implements IAnimal, Animator.AnimatorListener {

    private static final String TAG = PropertyValueAnimator.class.getSimpleName();
    private View mView;

    private ValueAnimator mValueAnimator;

    private int animatorPlaySort;

    public PropertyValueAnimator(View view) {
        mView = view;
    }

    @Override
    public void startLoopAnimal() {
        animatorPlaySort = 0;
        doNextAnimation();
    }

    @Override
    public void stopLoopAnimal() {
        if (mValueAnimator != null) {
            mValueAnimator.pause();
            mValueAnimator.cancel();
        }
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.i(TAG, "onAnimationEnd");
        animatorPlaySort++;
        doNextAnimation();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }


    private void doNextAnimation() {
        stopLoopAnimal();
        switch (animatorPlaySort) {
            case 0:
                startOfFloat();
                break;
            case 1:
                startOfObject();
                break;
        }
    }

    /**
     * 启动 ofFloat动画
     */
    private void startOfFloat() {
        mValueAnimator = ValueAnimator.ofFloat(0, 100.f);
        mValueAnimator.setInterpolator(new BounceInterpolator());
        mValueAnimator.setDuration(5000);//播放时长
        mValueAnimator.setStartDelay(300);//延迟播放
        mValueAnimator.setRepeatCount(0);//重放次数
        mValueAnimator.addUpdateListener(animation ->
                mView.setTranslationX((Float) animation.getAnimatedValue()));
        mValueAnimator.start();
        mValueAnimator.addListener(this);
    }

    /**
     * 启动ofObject动画
     */
    private void startOfObject() {
        // 创建初始动画的对象  & 结束动画的对象
        Point point1 = new Point(0, 0);
        Point point2 = new Point(50, 50);
        // 创建动画对象 & 设置参数
        mValueAnimator = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
        // 参数说明
        // 1. 自定义的估值器对象（TypeEvaluator 类型参数） - 下面会详细介绍
        // 2. 初始动画的对象
        // 3. 结束动画的对象
        mValueAnimator.setDuration(5000);//播放时长
        mValueAnimator.setStartDelay(300);//延迟播放
        mValueAnimator.setRepeatCount(0);//重放次数
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "animation:" + animation);
                Point point = (Point) animation.getAnimatedValue();
                mView.setTranslationX(point.getX());
                mView.setTranslationY(point.getY());
            }
        });

        mValueAnimator.start();
    }



}
