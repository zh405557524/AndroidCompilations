package com.soul.androidcontent.subassembly.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {
    private static final String TAG = TestService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "服务被创建");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "服务被绑定");
        return new Binder();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "服务解绑");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, "服务被销毁");
        super.onDestroy();
    }
}
