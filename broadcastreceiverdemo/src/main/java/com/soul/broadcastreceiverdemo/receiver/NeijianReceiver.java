package com.soul.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 广播监听者
 */
public class NeijianReceiver extends BroadcastReceiver {
    public NeijianReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String resultData = getResultData();
        System.out.println("我是内线,报告主席:" + resultData);
    }
}
