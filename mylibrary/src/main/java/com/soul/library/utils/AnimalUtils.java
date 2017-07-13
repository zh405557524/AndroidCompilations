package com.soul.library.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.animation.AnimationSet;

import java.util.WeakHashMap;

/**
 * * @author soul
 *
 * @项目名:MyApplication
 * @包名: com.soul.library.utils
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/25 21:24
 */

public class AnimalUtils {

    static WeakHashMap<Animator, Object> sAnimators = new WeakHashMap<Animator, Object>();

    static Animator.AnimatorListener sEndAnimListener = new Animator.AnimatorListener() {
        public void onAnimationStart(Animator animation) {
            sAnimators.put(animation, null);
        }

        public void onAnimationRepeat(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            sAnimators.remove(animation);
        }

        public void onAnimationCancel(Animator animation) {
            sAnimators.remove(animation);
        }
    };


    public static AnimatorSet createAnimatorSet() {
        AnimatorSet anim = new AnimatorSet();
        cancelOnDestroyActivity(anim);
        return anim;
    }
    public static AnimationSet createAnimationSet(boolean flag) {
        AnimationSet anim = new AnimationSet(flag);
        return anim;
    }



    public static void cancelOnDestroyActivity(Animator a) {
        a.addListener(sEndAnimListener);
    }
}
