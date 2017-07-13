package com.soul.servicedemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.soul.library.utils.LogUtils;

public class ScreenReceiver extends BroadcastReceiver {
    public ScreenReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)){
            LogUtils.i("屏幕锁定");
        }else if (Intent.ACTION_SCREEN_ON.equals(action)){
            LogUtils.i("屏幕解锁");
        }
    }
}
