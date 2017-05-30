package com.soul.animal.animal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.soul.animal.CommonUtil;
import com.soul.animal.MusicPlayAnimalView;
import com.soul.animal.R;


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

    public void startAlphaAnimal(View view, float alpha, int duration) {
        mStateAnimator = LauncherAnimUtils.createAnimatorSet();
        LauncherViewPropertyAnimator showAnimator = new LauncherViewPropertyAnimator(view);
        showAnimator.alpha(alpha)
                .setDuration(duration)
                .setInterpolator(mZoomInInterpolator);
        mStateAnimator.play(showAnimator);
        mStateAnimator.start();
    }

    /**
     * 音乐封面展开的动画
     */
    public void startAnimationToMusicCover(final Activity activity, final ImageView formView, ImageView toView, MusicPlayAnimalView showView) {
        //3获取起点和终点的坐标
        Rect formLocation = new Rect();
        formView.getGlobalVisibleRect(formLocation);
        Rect toLocation = new Rect();
        toView.getGlobalVisibleRect(toLocation);
        //1创建动画视图层
        final ViewGroup animLayout = createAnimLayout(activity);
        final ImageView imageView = new ImageView(activity);
        //复制ImageView
        final BitmapDrawable drawable = (BitmapDrawable) formView.getDrawable();
        final Bitmap bitmap = drawable.getBitmap();
        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(imageView.getResources(), bitmap);
        circularBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(circularBitmapDrawable);
        //2将需要移动的view添加到动画层
        animLayout.addView(imageView);
        View view = addViewToAnimLayout(animLayout, imageView, formLocation,
                CommonUtil.getDimens(activity, R.dimen.x64),
                CommonUtil.getDimens(activity, R.dimen.x64));

        //4动画逻辑
        int moveX = toLocation.left - formLocation.left + (toLocation.right - toLocation.left) / 2
                - (formLocation.right - formLocation.left) / 2;
        int moveY = toLocation.top - formLocation.top + ((toLocation.bottom - toLocation.top) / 2)
                - (formLocation.bottom - formLocation.top) / 2;
        mStateAnimator = LauncherAnimUtils.createAnimatorSet();
        LauncherViewPropertyAnimator formAnimator = new LauncherViewPropertyAnimator(view);
        formAnimator
                .translationX(moveX)
                .translationY(moveY)
                .scaleX(2.734375f)
                .scaleY(2.734375f)
                .setDuration(duration)
                .setInterpolator(mZoomInInterpolator);
        mStateAnimator.play(formAnimator);

        LauncherViewPropertyAnimator showAnimator = new LauncherViewPropertyAnimator(showView);
        showAnimator.alpha(1.0f)
                .setDuration(duration)
                .setInterpolator(mZoomInInterpolator);
        mStateAnimator.play(showAnimator);


        //5动画开始，动画结束
        mStateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setImageDrawable(null);
                bitmap.recycle();
                clearAnimLayout(activity, animLayout);
            }
        });
        mStateAnimator.start();
    }

    /**
     * 音乐封面收缩的动画
     */
    public void startAnimationToMainFromMusicCover(final Activity activity, ImageView toView, ImageView formView, MusicPlayAnimalView hideView) {
        //1获取起点和终点的坐标
        Rect formLocation = new Rect();
        formView.getGlobalVisibleRect(formLocation);
        Rect toLocation = new Rect();
        toView.getGlobalVisibleRect(toLocation);
        //2创建动画视图层
        final ViewGroup animLayout = createAnimLayout(activity);
        final ImageView imageView = new ImageView(activity);
        //复制ImageView
        imageView.setImageDrawable(formView.getDrawable());
        //2将需要移动的view添加到动画层
        animLayout.addView(imageView);
        View view = addViewToAnimLayout(animLayout, imageView, formLocation,
                CommonUtil.getDimens(activity, R.dimen.y175),
                CommonUtil.getDimens(activity, R.dimen.x175));

        //4动画逻辑
        int moveX = toLocation.left - formLocation.left + (toLocation.right - toLocation.left) / 2
                - (formLocation.right - formLocation.left) / 2;
        int moveY = toLocation.top - formLocation.top + ((toLocation.bottom - toLocation.top) / 2)
                - (formLocation.bottom - formLocation.top) / 2;
        mStateAnimator = LauncherAnimUtils.createAnimatorSet();
        LauncherViewPropertyAnimator formAnimator = new LauncherViewPropertyAnimator(view);
        formAnimator
                .translationX(moveX)
                .translationY(moveY)
                .scaleX(0.36571429f)
                .scaleY(0.36571429f)
                .setDuration(duration)
                .setInterpolator(mZoomInInterpolator);
        mStateAnimator.play(formAnimator);
        LauncherViewPropertyAnimator showAnimator = new LauncherViewPropertyAnimator(hideView);
        showAnimator.alpha(.0f)
                .setDuration(duration)
                .setInterpolator(mZoomInInterpolator);
        mStateAnimator.play(showAnimator);
        //5动画开始，动画结束
        mStateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setImageDrawable(null);
                clearAnimLayout(activity, animLayout);
            }
        });
        mStateAnimator.start();
    }


    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private ViewGroup createAnimLayout(Activity context) {
        ViewGroup rootView = (ViewGroup) context.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }


    private void clearAnimLayout(Activity context, View view) {
        ViewGroup rootView = (ViewGroup) context.getWindow().getDecorView();
        rootView.removeView(view);
    }


    private View addViewToAnimLayout(final ViewGroup vg, final View view,
                                     Rect location, int weight, int height) {
        int x = location.left;
        int y = location.top;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(weight, height);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
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
