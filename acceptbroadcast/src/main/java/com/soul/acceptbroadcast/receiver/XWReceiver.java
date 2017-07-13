package com.soul.acceptbroadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class XWReceiver extends BroadcastReceiver {
    public XWReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String news = intent.getStringExtra("news");
        Toast.makeText(context,"收到新闻了:"+news,Toast.LENGTH_SHORT).show();
    }
}
