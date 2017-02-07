package com.soul.acceptbroadcast.receiver.orderly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 有序广播 -农民
 */
public class NongMingReceiver extends BroadcastReceiver {
    public NongMingReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        System.out.println("我是农民兄弟："+resultData+"  感谢党，感谢金主席!");

    }
}
