package com.soul.androidcontent.customview.anim.objectanimator;

import android.view.View;

import com.soul.androidcontent.customview.anim.IAnimal;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 自定义属性动画
 * Author: zhuMing
 * CreateDate: 2020/7/10 10:51
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/10 10:51
 * UpdateRemark:
 */
public class CustomPropertyObjectAnimator implements IAnimal {


    private View mView;
    private MyObjectAnimator mMyObjectAnimator;

    public CustomPropertyObjectAnimator(View view) {
        mView = view;
    }

    @Override
    public void startLoopAnimal() {
        List<MyObjectAnimator> objectAnimations = getObjectAnimations();
        mMyObjectAnimator = objectAnimations.get(0);
        mMyObjectAnimator.setDuration(3000);
        mMyObjectAnimator.setTimeInterpolator(new LineInterpolator());
        mMyObjectAnimator.start();

    }

    @Override
    public void stopLoopAnimal() {

    }


    public List<MyObjectAnimator> getObjectAnimations() {
        ArrayList<MyObjectAnimator> objectAnimators = new ArrayList<>();
        //1、平移X
        MyObjectAnimator translationX = MyObjectAnimator.ofFloat(mView, "translationX", 0, 100, 0);
        MyObjectAnimator translationY = MyObjectAnimator.ofFloat(mView, "translationY", 0, 100, 0);
        objectAnimators.add(translationX);
        objectAnimators.add(translationY);
        //2、旋转
        MyObjectAnimator rotation = MyObjectAnimator.ofFloat(mView, "rotation", 0, 360, 0);
        objectAnimators.add(rotation);

        //3、缩放
        MyObjectAnimator scaleX = MyObjectAnimator.ofFloat(mView, "scaleX", 1.f, 2.2f, 1.f);
        objectAnimators.add(scaleX);

        //4、透明度
        MyObjectAnimator alpha = MyObjectAnimator.ofFloat(mView, "alpha", 1f, 0.5f, 1f);
        objectAnimators.add(alpha);
        return objectAnimators;
    }
}
