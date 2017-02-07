package com.soul.acceptbroadcast.receiver.orderly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 有序广播 -乡级
 */
public class xiagnjiReceiver extends BroadcastReceiver {
    public xiagnjiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        System.out.println("我是乡级部门："+resultData);
        setResultData("我是乡级部门:中央下发粮食，大家每人10斤");
    }
}
