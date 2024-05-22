package com.soul.androidcontent.customview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.soul.androidcontent.R;
import com.soul.androidcontent.customview.anim.IAnimal;
import com.soul.androidcontent.customview.anim.PropertyObjectAnimator;
import com.soul.androidcontent.customview.anim.PropertyValueAnimator;
import com.soul.androidcontent.customview.anim.TweenAnimation;
import com.soul.androidcontent.customview.anim.objectanimator.CustomPropertyObjectAnimator;
import com.soul.lib.test.ButtonTextFragment;

/**
 * Description:动画演示类
 * Author: zhuMing
 * CreateDate: 2020/7/8 11:08
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/8 11:08
 * UpdateRemark:
 */
public class AnimTestFragment extends ButtonTextFragment implements View.OnClickListener {


    private ImageView mImageView;
    private IAnimal mIAnimal;

    @Override
    public String getClassName() {
        return "动画演示";
    }

    @Override
    protected void initEvent() {
        addTextName("补间动画", this);
        addTextName("帧动画", this);
        addTextName("属性动画-value", this);
        addTextName("属性动画-object", this);
        addTextName("自定义属性动画", this);

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
            mImageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0);
            layoutParams.width = W;
            layoutParams.height = H;
            mImageView.setLayoutParams(layoutParams);
            mImageView.setImageResource(R.drawable.girl);
            addView(mImageView);
        }

    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (mIAnimal != null) {
            mIAnimal.stopLoopAnimal();
        }

        switch (tag) {
            case "补间动画":
                mIAnimal = new TweenAnimation(mImageView);
                break;
            case "帧动画":

                break;
            case "属性动画-value":
                mIAnimal = new PropertyValueAnimator(mImageView);

                break;
            case "属性动画-object":
                mIAnimal = new PropertyObjectAnimator(mImageView);
                break;
            case "自定义属性动画":
                mIAnimal = new CustomPropertyObjectAnimator(mImageView);
                break;
            default:
                mIAnimal = null;
                break;

        }

        if (mIAnimal != null) {
            mIAnimal.startLoopAnimal();
        }

    }


}
