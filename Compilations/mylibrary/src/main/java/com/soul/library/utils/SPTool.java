package com.soul.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Administrator
 * @date 2015-7-16上午10:33:12
 * @描述 Shared Preference 工具类
 * <p/>
 * 　svn author：$Author: heima09 $
 * svn version: $Rev: 93 $
 * svn update time: $Date: 2015-07-22 14:21:04 +0800 (Wed, 22 Jul 2015) $
 */
public class SPTool {
    /**
     * 存储字符串的数据到sp中
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        // name xml文件名 mode 文件的类型
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return 取对应key的值
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static String getString(Context context, String key) {
        if (context == null) {
            return "";
        }
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        return getString(context, key, "");
    }

    public static float getFloat(Context context, String key, float defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static void putFloat(Context context, String key, float value) {
        // name xml文件名 mode 文件的类型
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).apply();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        // name xml文件名 mode 文件的类型
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return 取对应key的值
     */
    public static Boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }


    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        // name xml文件名 mode 文件的类型
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }


}
