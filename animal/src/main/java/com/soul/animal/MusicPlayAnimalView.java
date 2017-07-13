package com.soul.animal;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @描述：播放器播放动画
 * @作者：祝明
 * @创建时间：2017/4/21 10:52
 */

public class MusicPlayAnimalView extends FrameLayout {

    /**
     * music_play_animal_view
     * DVD旋转一周的时长
     */
    private static int sDurationDVDMillis = 25000;
    /**
     * 唱针旋转时长
     */
    private static int sDurationPhonographMillis = 800;

    private static final int STATE_NONE = 0;//默认状态
    private static final int STATE_START = 1;//动画开启
    private static final int STATE_Light = 2;//亮光动画开启
    private static final int STATE_PAUSE = 3;//动画暂停
    private static final int STATE_STOP = 4;//动画停止

    @BindView(R.id.fl_dvd)
    FrameLayout mDvd;
    /**
     * 唱针
     */
    @BindView(R.id.iv_phonograph)
    ImageView mPhonograph;

    /**
     * 发光的点
     */
    @BindView(R.id.iv_light)
    ImageView mLight;

    @BindView(R.id.iv_phonograph16)
    ImageView mPhonograph16;

    /**
     * 海报
     */
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    /**
     * 根view
     */
    private View mRootView;
    /**
     * DVD旋转动画
     */
    private ObjectAnimator refreshingAnimation;
    private RotateAnimation mPhonographAnimation;
    private AnimationDrawable mLightDrawable;
    /**
     * 唱片旋转的角度
     */
    private float currentValue;


    private int mAnimalState = STATE_NONE;
    private LinearInterpolator mLir;

    private boolean isLightAnimal = false;
    /**
     * 第一次进入
     */
    private boolean isLightFirst = false;


    public MusicPlayAnimalView(@NonNull Context context) {
        super(context, null);
    }

    public MusicPlayAnimalView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicPlayAnimalView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initData();
        initEvent();
    }


    private void initView(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_music_play, this);
        ButterKnife.bind(mRootView);
    }

    private void initData() {
        // 添加匀速转动动画
        mLir = new LinearInterpolator();
        mDvd.setPivotX(CommonUtil.getDimens(getContext(), R.dimen.y204));
        mDvd.setPivotY(CommonUtil.getDimens(getContext(), R.dimen.x261));

        //唱针动画
        mPhonographAnimation =
                (RotateAnimation) AnimationUtils.loadAnimation(getContext(), R.anim.phonograph_rotating);
        mPhonographAnimation.setDuration(sDurationPhonographMillis);
        mPhonographAnimation.setInterpolator(mLir);
    }

    private void initEvent() {

        //唱针
        mPhonographAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("Tag", "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isLightFirst) {
                    mAnimalState = STATE_Light;
                    startLightAnimation();
                } else {
                    isLightFirst = false;
                }
                isLightAnimal = true;
                Log.i("Tag", "onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("Tag", "onAnimationRepeat");
            }
        });
    }

    public void resetParam() {

    }

    /**
     * 设置播放状态对应的界面
     *
     * @param state
     */
    public void setStateView(int state) {
        switch (state) {//为0 不做处理
            case 1://播放
                mAnimalState = STATE_START;
                isLightFirst = true;
                startDVDAnimal();
                mPhonographAnimation.setDuration(0);
                mPhonograph.setVisibility(GONE);
                mPhonograph.startAnimation(mPhonographAnimation);
                mPhonograph16.setVisibility(VISIBLE);
                mLight.setImageResource(R.drawable.light_15);
                break;
            case 2://暂停
                mAnimalState = STATE_PAUSE;
                isLightFirst = true;
                mPhonographAnimation.setDuration(0);
                mPhonograph.setVisibility(GONE);
                mPhonograph16.setVisibility(VISIBLE);
                mPhonograph.startAnimation(mPhonographAnimation);
                mLight.setImageResource(R.drawable.light_15);
                break;
        }
    }

    /**
     * 动画开启
     */
    public synchronized void start() {
        //DVD动画
        if (mAnimalState == STATE_NONE) {
            mPhonographAnimation.setDuration(sDurationPhonographMillis);
            mPhonograph.startAnimation(mPhonographAnimation);
        }
        if (mAnimalState != STATE_START) {
            if (mAnimalState != STATE_PAUSE) {
                stop();
            }
            startDVDAnimal();
            mAnimalState = STATE_START;
        }
    }

    /**
     * 动画暂停
     */
    public void pause() {
        if (mAnimalState != STATE_NONE && refreshingAnimation != null) {
            refreshingAnimation.cancel();
            mAnimalState = STATE_PAUSE;
        }
    }

    /**
     * 动画停止
     */
    public void stop() {
        if (refreshingAnimation != null) {
            refreshingAnimation.end();
            currentValue = 0;
            mAnimalState = STATE_STOP;
        }
    }

    /**
     * 下一首
     */
    public void next() {
        if (isLightAnimal) {
            startLightAnimation();
        }
    }

    /**
     * 上一首
     */
    public void previous() {
        if (isLightAnimal) {
            startLightAnimation();
        }
    }

    /**
     * 开启DVD旋转动画
     */
    private void startDVDAnimal() {
        refreshingAnimation = ObjectAnimator.ofFloat(mDvd, "Rotation",
                currentValue - 360, currentValue);
        refreshingAnimation.setRepeatCount(-1);
        refreshingAnimation.setDuration(sDurationDVDMillis);
        refreshingAnimation.setInterpolator(mLir);
        refreshingAnimation.start();
        refreshingAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 监听动画执行的位置，以便下次开始时，从当前位置开始
                currentValue = (Float) animation.getAnimatedValue();
            }
        });
    }


    /**
     * 开启光点展开动画
     */
    private void startLightAnimation() {
        if (mLightDrawable == null) {
            mLight.setImageResource(R.drawable.music_play_light);
            mLightDrawable = (AnimationDrawable) mLight.getDrawable();
            mLightDrawable.setEnterFadeDuration(0);
        }
        if (mLightDrawable.isRunning()) {
            mLightDrawable.stop();
        }
        mLightDrawable.start();
    }

    public void onDestroy() {
        if (refreshingAnimation != null) {
            refreshingAnimation.cancel();
            refreshingAnimation = null;
        }
        if (mPhonograph != null) {
            mPhonograph.clearAnimation();
        }
        if (mLight != null) {
            mLight.clearAnimation();
        }
        mLightDrawable = null;
        currentValue = 0;
        mAnimalState = STATE_NONE;
        if (ivCover != null) {
            Drawable drawable = ivCover.getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
        }
    }


    public void setIvCover(String thumbs) {
        //        ImageUtil.setCircleImageView(ivCover, thumbs);
    }

    /**
     * 获取封面
     *
     * @return
     */
    public ImageView getMusicCover() {
        return ivCover;
    }

}
