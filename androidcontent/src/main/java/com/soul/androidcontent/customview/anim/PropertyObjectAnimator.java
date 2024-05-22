package com.soul.androidcontent.customview.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 属性动画-ObjectAnimator演示
 * Author: zhuMing
 * CreateDate: 2020/7/9 15:24
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/9 15:24
 * UpdateRemark:
 */
public class PropertyObjectAnimator implements IAnimal {


    private View mView;

    public PropertyObjectAnimator(View view) {
        mView = view;
    }

    @Override
    public void startLoopAnimal() {

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(5000);
        animatorSet.setInterpolator(new LinearInterpolator());

        List<ObjectAnimator> objectAnimations = getObjectAnimations();
        AnimatorSet.Builder play = animatorSet.play(objectAnimations.get(0));
        for (int i = 1; i < objectAnimations.size(); i++) {
            play = play.after(objectAnimations.get(i));
        }
        animatorSet.start();
    }

    @Override
    public void stopLoopAnimal() {

    }


    public List<ObjectAnimator> getObjectAnimations() {
        ArrayList<ObjectAnimator> objectAnimators = new ArrayList<>();
        //1、平移X
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mView, "translationX", 0, 100, 0);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mView, "translationY", 0, 100, 0);
        objectAnimators.add(translationX);
        objectAnimators.add(translationY);
        //2、旋转
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mView, "rotation", 0, 360, 0);
        objectAnimators.add(rotation);

        //3、缩放
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mView, "scaleX", 1.f, 2.2f, 1.f);
        objectAnimators.add(scaleX);

        //4、透明度
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mView, "alpha", 1f, 0.5f, 1f);
        objectAnimators.add(alpha);
        return objectAnimators;
    }
}
