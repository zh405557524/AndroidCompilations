package com.soul.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.soul.library.utils.LogUtils;
import com.soul.library.utils.ToastUtils;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.servicedemo
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/2/5 16:37
 */

public class TextService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i("服务onCreate");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("服务onStartCommand,onStart");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("服务onDestroy");
    }


    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.i("服务onUnbind");
        return super.onUnbind(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        LogUtils.i("服务onBind");
        return new MyBinder();
    }


    /**
     * 中间人,可以执行服务里的方法
     */
   private class MyBinder extends Binder implements IService {
        public void call(int money) {
            if (money >= 2000) {
                methodInService();
            } else {
                LogUtils.i("钱太少");
            }
        }

        public void playMaJiang(){
            LogUtils.i("我是小蜜");

        }

        public void 洗桑拿(){
            LogUtils.i("洗桑拿");
        }

        @Override
        public void callMethodInService(int money) {
            call(money);
        }
    }


    /**
     * 服务里面的方法
     */
    public void methodInService() {
        LogUtils.i("methodInService");
        ToastUtils.showLongToast("methodInService:我是领导，我给你办证");
    }


}
