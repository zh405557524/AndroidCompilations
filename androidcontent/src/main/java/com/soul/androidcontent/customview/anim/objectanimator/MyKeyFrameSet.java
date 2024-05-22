package com.soul.androidcontent.customview.anim.objectanimator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * Author: 祝明
 * CreateDate: 2019/4/26 下午1:33
 * UpdateUser:
 * UpdateDate: 2019/4/26 下午1:33
 * UpdateRemark:
 */
class MyKeyFrameSet {

    //类型估值器
    TypeEvaluator mEvaluator;

    MyFloatKeyFrame mFirstKeyframe;

    List<MyFloatKeyFrame> mKeyframes;


    public MyKeyFrameSet(MyFloatKeyFrame... keyFrames) {

        mKeyframes = Arrays.asList(keyFrames);
        mFirstKeyframe = keyFrames[0];
        mEvaluator = new FloatEvaluator();

    }


    public static MyKeyFrameSet ofFloat(float[] values) {

        final int numKeyframes = values.length;

        final MyFloatKeyFrame[] keyframes = new MyFloatKeyFrame[numKeyframes];
        keyframes[0] = new MyFloatKeyFrame(0, values[0]);
        for (int i = 1; i < numKeyframes; i++) {
            keyframes[i] = new MyFloatKeyFrame((float) i / (numKeyframes - 1), values[i]);
        }

        return new MyKeyFrameSet(keyframes);
    }


    public Object getValue(float fraction) {
         MyFloatKeyFrame prevKeyframe = mFirstKeyframe;

        for (int i = 1; i < mKeyframes.size(); i++) {
            MyFloatKeyFrame nextKeyframe = mKeyframes.get(i);
            if (fraction < nextKeyframe.getFraction()) {
                return mEvaluator.evaluate(fraction, prevKeyframe.getValue(),
                        nextKeyframe.getValue());
            }
            prevKeyframe = nextKeyframe;

        }


        return null;
    }
}
