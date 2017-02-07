package com.soul.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.soul.library.utils.LogUtils;
import com.soul.servicedemo.IRemoteService;

public class RemoteService extends Service {
    public RemoteService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i("我是远程服务，我被创建了");
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("我是远程服务，我被绑定了");


        return new MyBinder();
    }


    class MyBinder extends IRemoteService.Stub {

        @Override
        public void callMethodInService() {
            methodInService();
        }

    }


    public void methodInService(){
        LogUtils.i("我是远程服务，我的方法被调用了 ");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("我是远程服务，我被销毁了  ");
    }
}
