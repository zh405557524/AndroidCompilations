package com.soul.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * app 安装卸载监听
 */
public class AppStateListener extends BroadcastReceiver {
    public AppStateListener() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_ADDED".equals(action)){
            Toast.makeText(context,"应该被安装",Toast.LENGTH_SHORT).show();
        }else if ("android.intent.action.PACKAGE_REPLACED".equals(action)){
            Toast.makeText(context,"应该被更新",Toast.LENGTH_SHORT).show();
        }else if ("android.intent.action.PACKAGE_REMOVED".equals(action)){
            Toast.makeText(context,"应该被卸载",Toast.LENGTH_SHORT).show();
        }


    }
}
