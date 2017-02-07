package com.soul.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.soul.servicedemo.receiver.ScreenReceiver;

public class ScrennService extends Service {
    public ScrennService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private IntentFilter mIntentFilter;
    private ScreenReceiver mScreenReceiver;
    @Override
    public void onCreate() {
        super.onCreate();



        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mIntentFilter.addAction(Intent.ACTION_SCREEN_ON);

        mScreenReceiver = new ScreenReceiver();
        registerReceiver(mScreenReceiver,mIntentFilter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mScreenReceiver);
    }


}
