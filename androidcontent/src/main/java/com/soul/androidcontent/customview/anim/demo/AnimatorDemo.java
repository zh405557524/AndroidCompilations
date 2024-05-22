package com.soul.androidcontent.customview.anim.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

/**
 * Description: 动画演示
 * Author: zhuMing
 * CreateDate: 2020/7/10 15:42
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/10 15:42
 * UpdateRemark:
 */
public class AnimatorDemo implements Animator.AnimatorListener {


    private AnimatorDemo.AnimatorListener mAnimatorListener;

    /**
     * 两个View 平切动画
     *
     * @param comeView  将要显示的View
     * @param leaveView 将要隐藏的View
     */
    public void startTwoViewTruncation(View comeView, View leaveView, AnimatorDemo.AnimatorListener animatorListener) {

        mAnimatorListener = animatorListener;
        AnimatorSet animatorSet = new AnimatorSet();
        //1、显示的View 向左平移，并逐步显示
        int comeWidth = comeView.getWidth();
        ObjectAnimator comeTranslationX = ObjectAnimator.ofFloat(comeView, "translationX", comeWidth, 0);
        ObjectAnimator comeAlpha = ObjectAnimator.ofFloat(comeView, "alpha", 0, 1.f);

        int leaveViewWidth = leaveView.getWidth();
        //2、显示的View 向右平移，并逐步隐藏
        ObjectAnimator leaveTranslationX = ObjectAnimator.ofFloat(leaveView, "translationX", 0, leaveViewWidth);
        ObjectAnimator leaveAlpha = ObjectAnimator.ofFloat(leaveView, "alpha", 1.f, 0);

        animatorSet.playTogether(comeTranslationX, comeAlpha, leaveTranslationX, leaveAlpha);
        animatorSet.setDuration(600);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.addListener(this);
        animatorSet.start();

    }

    /**
     * 启动一个缩放动画
     *
     * @param imageView
     * @param animatorListener
     */
    public void startScale(ImageView imageView, AnimatorDemo.AnimatorListener animatorListener) {
        mAnimatorListener = animatorListener;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mAnimatorListener != null) {
            mAnimatorListener.onAnimationEnd(animation);
            mAnimatorListener = null;
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }


    public interface AnimatorListener {
        void onAnimationEnd(Animator animation);
    }
}











