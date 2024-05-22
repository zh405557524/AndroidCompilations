package com.soul.androidcontent.customview.anim.objectanimator;

/**
 * Description:关键帧
 * Author: 祝明
 * CreateDate: 2019/4/26 下午1:35
 * UpdateUser:
 * UpdateDate: 2019/4/26 下午1:35
 * UpdateRemark:
 */
public class MyFloatKeyFrame {

    float mFraction;
    Class mValueType;
    float mValue;


    public MyFloatKeyFrame(float fraction, float value) {
        mFraction = fraction;
        mValue = value;
        mValueType = float.class;
    }

    public float getFraction() {
        return mFraction;
    }

    public void setValue(float value) {
        mValue = value;
    }

    public float getValue() {
        return mValue;
    }

    public Class getValueType() {
        return mValueType;
    }
}
