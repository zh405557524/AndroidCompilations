package com.soul.animal.animal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * * @author soul
 *
 * @描述：播放器的伸缩动画
 * @作者：祝明
 * @创建时间：2017/5/15 13:45
 */

public class MusicStateAnimation {

    @Thunk
    AnimatorSet mStateAnimator;

    @Thunk
    final ZoomInInterpolator mZoomInInterpolator = new ZoomInInterpolator();

    private boolean isDebug = true;
    private long duration = 600;

    /**
     * 音乐封面展开的动画
     */
    public void startAnimationToMusicCover(final ImageView formView, FrameLayout toView, LinearLayout goneView) {
        mStateAnimator = LauncherAnimUtils.createAnimatorSet();
        int[] formLocation = new int[2];
        int[] toLocation = new int[2];
        formView.getLocationOnScreen(formLocation);
        toView.getLocationOnScreen(toLocation);
        LauncherViewPropertyAnimator goneAnimator = new LauncherViewPropertyAnimator(goneView)
                .alpha(0.f);
        LauncherViewPropertyAnimator toAnimator = new LauncherViewPropertyAnimator(goneView)
                .alpha(1.f);
        mStateAnimator.play(toAnimator);
        mStateAnimator.play(goneAnimator);

        LauncherViewPropertyAnimator formAnimator = new LauncherViewPropertyAnimator(formView);
        formAnimator
                .translationX(100)
                .translationY(-100)
                .alpha(1.f)
                .setDuration(duration)
                .setInterpolator(mZoomInInterpolator);
        mStateAnimator.play(formAnimator);

        mStateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        mStateAnimator.start();
    }

    /**
     * 音乐封面收缩的动画
     */
    public void startAnimationToMainFromMusicCover(final ImageView toView, FrameLayout formView, LinearLayout showView) {
        mStateAnimator = LauncherAnimUtils.createAnimatorSet();
        int[] formLocation = new int[2];
        int[] toLocation = new int[2];
        formView.getLocationOnScreen(formLocation);
        toView.getLocationOnScreen(toLocation);
        LauncherViewPropertyAnimator goneAnimator = new LauncherViewPropertyAnimator(showView)
                .alpha(1.f);
        mStateAnimator.play(goneAnimator);

        LauncherViewPropertyAnimator formAnimator = new LauncherViewPropertyAnimator(toView);
        formAnimator
                .translationX(-100)
                .translationY(100)
                .alpha(1.f)
                .setDuration(duration)
                .setInterpolator(mZoomInInterpolator);
        mStateAnimator.play(formAnimator);
        mStateAnimator.start();
    }


    /**
     * This interpolator emulates the rate at which the perceived scale of an object changes
     * as its distance from a camera increases. When this interpolator is applied to a scale
     * animation on a view, it evokes the sense that the object is shrinking due to moving away
     * from the camera.
     */
    class ZInterpolator implements TimeInterpolator {
        private float focalLength;

        public ZInterpolator(float foc) {
            focalLength = foc;
        }

        public float getInterpolation(float input) {
            return (1.0f - focalLength / (focalLength + input)) /
                    (1.0f - focalLength / (focalLength + 1.0f));
        }
    }

    /**
     * The exact reverse of ZInterpolator.
     */
    class InverseZInterpolator implements TimeInterpolator {
        private ZInterpolator zInterpolator;

        public InverseZInterpolator(float foc) {
            zInterpolator = new ZInterpolator(foc);
        }

        public float getInterpolation(float input) {
            return 1 - zInterpolator.getInterpolation(1 - input);
        }
    }

    /**
     * InverseZInterpolator compounded with an ease-out.
     */
    class ZoomInInterpolator implements TimeInterpolator {
        private final InverseZInterpolator inverseZInterpolator = new InverseZInterpolator(0.35f);
        private final DecelerateInterpolator decelerate = new DecelerateInterpolator(3.0f);

        public float getInterpolation(float input) {
            return decelerate.getInterpolation(inverseZInterpolator.getInterpolation(input));
        }
    }
}
