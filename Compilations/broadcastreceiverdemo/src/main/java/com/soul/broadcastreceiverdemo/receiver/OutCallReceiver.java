package com.soul.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 *电话打出监听
 */
public class OutCallReceiver extends BroadcastReceiver {
    public OutCallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("收到外拨电话的广播了");
        String number = getResultData();
        System.out.println(number);
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String ipNumber = sp.getString("ipNumber", "");

        setResultData(ipNumber+number);

    }
}
