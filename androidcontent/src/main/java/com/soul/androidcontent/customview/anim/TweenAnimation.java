package com.soul.androidcontent.customview.anim;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Description: 补间动画演示
 * Author: zhuMing
 * CreateDate: 2020/7/8 11:17
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/8 11:17
 * UpdateRemark:
 */
public class TweenAnimation implements IAnimal {

    private View mView;
    private AnimationSet mAnimationSet;

    public TweenAnimation(View view) {
        if (view == null) {
            throw new RuntimeException("view is null");
        }
        mView = view;
    }

    /**
     * 启动循环动画
     */
    public void startLoopAnimal() {
        mAnimationSet = new AnimationSet(true);
        mAnimationSet.addAnimation(getTranslateAnimation());
        mAnimationSet.addAnimation(getRotateAnimation());
        mAnimationSet.addAnimation(getScaleAnimation());
        mAnimationSet.addAnimation(getAlphaAnimation());
        mAnimationSet.setDuration(3000);
        mAnimationSet.setInterpolator(new LinearInterpolator());
        mAnimationSet.setRepeatMode(-1);

        mView.startAnimation(mAnimationSet);

    }

    /**
     * 停止循环动画
     */
    public void stopLoopAnimal() {
        if (mAnimationSet != null) {
            mAnimationSet.cancel();
        }
    }

    /**
     * 获取平移动画
     *
     * @return
     */
    private TranslateAnimation getTranslateAnimation() {
        return new TranslateAnimation(0, 100, 0, 100);
    }

    /**
     * 获取旋转动画
     *
     * @return
     */
    private RotateAnimation getRotateAnimation() {
        return new RotateAnimation(0, 180,
                mView.getWidth() / 2.f, mView.getHeight() / 2.f);
    }

    /**
     * 获取旋转动画
     *
     * @return
     */
    private ScaleAnimation getScaleAnimation() {
        return new ScaleAnimation(0.5f, 1.2f, 0.5f, 1.2f,
                mView.getWidth() / 2.f, mView.getHeight() / 2.f);
    }

    /**
     * 获取透明动画
     *
     * @return
     */
    private AlphaAnimation getAlphaAnimation() {
        return new AlphaAnimation(0.5f, 10.f);
    }

}
