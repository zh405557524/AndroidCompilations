package com.soul.acceptbroadcast.receiver.orderly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 有序广播 -市级
 */
public class ShijiReceiver extends BroadcastReceiver {
    public ShijiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        System.out.println("我是市级部门："+resultData);

        setResultData("我是市级部门:中央下发粮食，大家每人200斤");

    }
}
