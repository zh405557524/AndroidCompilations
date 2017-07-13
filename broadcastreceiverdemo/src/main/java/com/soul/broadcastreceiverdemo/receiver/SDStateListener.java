package com.soul.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * SD卡监听
 */
public class SDStateListener extends BroadcastReceiver {
    public SDStateListener() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.MEDIA_MOUNTED")){
            Toast.makeText(context,"sd卡挂载了，微信自定义表情可以使用了",Toast.LENGTH_SHORT).show();
        }else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)){
            Toast.makeText(context,"sd卡卸载了，微信自定义表情暂不可用",Toast.LENGTH_SHORT).show();
        } else  if (action.equals(Intent.ACTION_MEDIA_REMOVED)){
            Toast.makeText(context,"sd被拔出了，你是不是闲了",Toast.LENGTH_SHORT).show();
        }

    }
}
