package com.soul.androidcontent.customview;

import android.animation.Animator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.soul.androidcontent.R;
import com.soul.androidcontent.customview.anim.demo.AnimatorDemo;
import com.soul.lib.test.ButtonTextFragment;

/**
 * Description: 动画实战演示
 * Author: zhuMing
 * CreateDate: 2020/7/10 15:59
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/10 15:59
 * UpdateRemark:
 */
public class AnimDemoFragment extends ButtonTextFragment implements View.OnClickListener, AnimatorDemo.AnimatorListener {


    private ImageView mImageView;
    private ImageView mImageView1;
    private AnimatorDemo mAnimatorDemo;
    private FrameLayout mFrameLayout;

    @Override
    public String getClassName() {

        return "动画实战演示";
    }

    @Override
    protected void initEvent() {
        addTextName("平切动画", this);
        addTextName("缩放动画", this);
        Context context = getContext();
        if (context != null) {
            int W, H;
            W = 0;
            H = 0;
            final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                W = H = (displayMetrics.widthPixels - 64) / 2;
            }

            mFrameLayout = new FrameLayout(getContext());

            mImageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0);
            layoutParams.width = W;
            layoutParams.height = H;
            mFrameLayout.setLayoutParams(layoutParams);
            mImageView.setLayoutParams(layoutParams);
            mImageView.setImageResource(R.drawable.girl);
            mFrameLayout.addView(mImageView);
            mImageView1 = new ImageView(context);
            mImageView1.setLayoutParams(layoutParams);
            mImageView1.setImageResource(R.drawable.meinv);
            mImageView1.setAlpha(0.f);
            mFrameLayout.addView(mImageView1);
            addView(mFrameLayout);
        }


        mAnimatorDemo = new AnimatorDemo();
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        switch (tag) {
            case "平切动画":
                mAnimatorDemo.startTwoViewTruncation(mImageView1, mImageView, this);
                break;
            case "缩放动画":
                mAnimatorDemo.startScale(mImageView, this);
                break;
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        //        mImageView1.setAlpha(0.f);
        //        mImageView.setAlpha(1.f);
        //        mImageView.setTranslationX(0);
        //        mImageView1.setTranslationX(0);
    }
}
