package com.soul.androidcontent.customview.anim.objectanimator;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Description:
 * Author: 祝明
 * CreateDate: 2019/4/26 上午10:58
 * UpdateUser:
 * UpdateDate: 2019/4/26 上午10:58
 * UpdateRemark:
 */
public class MyObjectAnimator implements VSYNCManger.AnimationFrameCallback {

    private static final String TAG = "tuch";
    private long mStartTime = -1;
    private long mDuration = 0;
    private WeakReference<View> target;

    MyFloatPropertyValuesHolder mMyFloatPropertyValuesHolder;
    private long mStartTime1;

    private float index;

    private TimeInterpolator mTimeInterpolator;


    public MyObjectAnimator(View view, String propertyName, float... values) {
        target = new WeakReference<View>(view);
        mMyFloatPropertyValuesHolder = new MyFloatPropertyValuesHolder(propertyName, values);
    }


    public static MyObjectAnimator ofFloat(View view, String propertyName, float... values) {

        final MyObjectAnimator anim = new MyObjectAnimator(view, propertyName, values);

        return anim;
    }

    public void start() {
        mMyFloatPropertyValuesHolder.setupSetter(target);
        mStartTime1 = System.currentTimeMillis();
        VSYNCManger.getInstance().add(this);
        //初始值
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    //每隔16ms 执行一次
    @Override
    public boolean doAnimationFrame(long currentTime) {
        final long total = mDuration / 16;
        //执行百分比
        float fraction = (index++) / total;
        if (mTimeInterpolator != null) {
            fraction = mTimeInterpolator.getInterpolation(fraction);
        }
        if (index >= total) {
            index = 0;
        }
        mMyFloatPropertyValuesHolder.setAnimatedValue(target.get(), fraction);

        return false;
    }

    public void setTimeInterpolator(TimeInterpolator timeInterpolator) {
        mTimeInterpolator = timeInterpolator;
    }

}
















































