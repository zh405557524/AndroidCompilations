package com.soul.library.utils;


import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.soul.library.BaseApplication;

/**
 * Toast 的封装类
 * <p/>
 * 2013-07-27
 *
 * @author WuMeng
 * @version 1.0
 */
public class ToastUtil {

    private static Toast mToast;

    /**
     * Toast短时间的提示
     *
     * @param info 需要显示的文字
     */
    public static void showShort(final String info) {
        if (StringUtils.isNotEmpty(info)) {
            BaseApplication.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    if (null == mToast) {
                        mToast = Toast.makeText(BaseApplication.getContext(), info, Toast.LENGTH_SHORT);
                    }
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText(info);
                    mToast.show();
                }
            });
        }
    }

    /**
     * Toast长时间的提示
     *
     * @param info 需要显示的文字
     */
    public static void showLong(String info) {
        if (StringUtils.isNotEmpty(info)) {
            if (null == mToast) {
                mToast = Toast.makeText(BaseApplication.getContext(), info, Toast.LENGTH_LONG);
            }
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setText(info);
            mToast.show();
        }
    }


    /**
     * toast定制化显示
     *
     * @param info
     * @param offsetY
     */
    public static void showShort(String info, int offsetx, int offsetY) {
        if (StringUtils.isNotEmpty(info)) {
            if (null == mToast) {
                mToast = Toast.makeText(BaseApplication.getContext(), info, Toast.LENGTH_SHORT);
            }
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setText(info);
            mToast.setGravity(Gravity.BOTTOM, offsetx, offsetY);
            mToast.show();
        }
    }

    /**
     * toast设置view显示
     *
     * @param view
     */
    public static void showShort(View view, int offsetx) {
        if (view != null) {
            if (null == mToast) {
                mToast = Toast.makeText(BaseApplication.getContext(), "", Toast.LENGTH_SHORT);
            }
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(view);
            mToast.setGravity(Gravity.BOTTOM, 0, 0);
            mToast.show();
        }
    }
}
