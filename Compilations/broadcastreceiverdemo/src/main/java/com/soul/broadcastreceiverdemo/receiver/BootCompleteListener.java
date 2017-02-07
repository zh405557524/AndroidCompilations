package com.soul.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.soul.broadcastreceiverdemo.MainActivity;

/**
 * 开机广播
 */
public class BootCompleteListener extends BroadcastReceiver {
    public BootCompleteListener() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //收到开机广播了，开启中奖的界面。
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
