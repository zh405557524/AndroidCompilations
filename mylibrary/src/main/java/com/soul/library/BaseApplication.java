package com.soul.library;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;

import com.soul.library.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2015/12/14 20:34
 */
public class BaseApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static long mMainThreadId;
    private static Thread mMainThread;
    public static Map<String, Long> map;

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    @Override
    public void onCreate() {
        // 创建一些常见的变量
        // 1 上下文

        mContext = getApplicationContext();
        // 2 创建一个handler
        mHandler = new Handler() {

        };
        // 3 的到一个主线程 id
        mMainThreadId = Process.myTid();
        // 4 得到主线程
        mMainThread = Thread.currentThread();
        //                registerException();
        Utils.init(mContext);
        super.onCreate();

    }

    private void registerException() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

                //获取手机的配置信息
                //动态获取手机配置
                StringWriter sw = new StringWriter();
                Class type = Build.class;
                Field[] declaredFields = type.getDeclaredFields();
                for (Field field : declaredFields) {
                    try {
                        sw.append(field.getName() + ":" + field.get(null) + "\n");
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                PrintWriter pw = new PrintWriter(sw);
                // 异常的捕获
                System.out.println(ex);
                //记录异常信息
                //写到文件中,sd卡
                File file = new File(Environment.getExternalStorageDirectory(), "excep.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    PrintWriter out = new PrintWriter(pw);
                    ex.printStackTrace(out);
                    // pw 写到  fos中
                    fos.write(sw.toString().getBytes());
                    out.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Process.killProcess(Process.myPid());
            }
        });
    }
}
