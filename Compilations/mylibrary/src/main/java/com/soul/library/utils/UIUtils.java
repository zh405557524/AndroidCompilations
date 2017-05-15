package com.soul.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;

import com.soul.library.BaseApplication;

import java.lang.reflect.Method;


/**
 * @author soul
 * @作者：祝明
 * @描述： 和Ui相关的一些静态工具的方法
 * @创建时间：2015-8-15 下午8:51:21
 */
public class UIUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return BaseApplication.getContext();
    }


    /**
     * 得到主线程的handler
     */
    public static Handler getHandler() {

        return BaseApplication.getHandler();
    }

    /**
     * 得到resouce对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的一个字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到string.xml中的一个字符串数组
     */
    public static String[] getStringArr(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到color.xml中的颜色值
     */
    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    /**
     * 得到应用程序的包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 得到主线程id
     */
    public static long getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    /**
     * 得到一个主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return BaseApplication.getHandler();
    }

    /**
     * 安全的执行一个task
     */
    public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();
        long mainThreadId = getMainThreadId();
        // 如果当前线程是主线程
        if (curThreadId == mainThreadId) {
            task.run();
        } else {
            // 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }


    /**
     * 获取虚拟按键栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    private static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }


    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    /**
     * get devices screen width
     *
     * @param activity@return device screen width
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * get devices screen height
     *
     * @param activity activity
     * @return device screen height
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * get decorView. boring Notes
     *
     * @param activity activity
     * @return decorView
     */
    public static View getDecorView(Activity activity) {
        return activity == null ? null : activity.getWindow().getDecorView();
    }



    public static float dpiFromPx(int size, DisplayMetrics metrics) {
        float densityRatio = (float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT;
        return (size / densityRatio);
    }

    public static int pxFromDp(float size, DisplayMetrics metrics) {
        return (int) Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                size, metrics));
    }

    public static int pxFromSp(float size, DisplayMetrics metrics) {
        return (int) Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                size, metrics));
    }


    /**
     * get status bar height
     *
     * @param activity activity
     * @return status height
     */
    public static int getStatusBarHeight(Activity activity) {
        if (activity == null) {
            return 0;
        }
        View view = activity.getWindow().getDecorView();
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        if (rect.top <= 0) {
            rect.top = getStatusBarHeight();
        }
        return rect.top;
    }

    /**
     * get status bar height by resource
     * @return status bar height
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * get current root view screen shot
     *
     * @param activity activity
     * @return screen shot bitmap
     */
    public static Bitmap getCurrentScreenShot(Activity activity) {
        return getCurrentScreenShot(activity, false);
    }

    public static Bitmap getCurrentScreenShot(Activity activity, boolean containStatusBar) {
        if (activity == null) {
            return null;
        }
        final View view = getDecorView(activity);
        view.buildDrawingCache();
        view.setDrawingCacheEnabled(true);
        int height = getScreenHeight(activity);
        int y=0;
        if (!containStatusBar) {
            y=getStatusBarHeight(activity);
            height -= getStatusBarHeight(activity);
        }
        if (hasNavigationBar(activity.getResources())) {
            y=0;
            height = getScreenHeight(activity)+
                    getNavigationBarSize(activity.getResources());
        }
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                y, getScreenWidth(activity),
                height);
        view.destroyDrawingCache();
        return bmp;
    }
    public static int getNavigationBarSize(Resources resources) {
        final boolean isMeiZu = Build.MANUFACTURER.equals("Meizu");
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        return resourceId > 0 ? isMeiZu?0:resources.getDimensionPixelSize(resourceId) : 0;
    }
    public static boolean hasNavigationBar(Resources resources) {
        int hasNavBarId = resources.getIdentifier("config_showNavigationBar",
                "bool", "android");
        return hasNavBarId > 0 && resources.getBoolean(hasNavBarId);
    }





}
