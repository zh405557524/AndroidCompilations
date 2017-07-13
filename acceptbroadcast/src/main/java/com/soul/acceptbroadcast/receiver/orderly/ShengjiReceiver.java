package com.soul.acceptbroadcast.receiver.orderly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 有序广播 -省级
 */
public class ShengjiReceiver extends BroadcastReceiver {
    public ShengjiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String resultData = getResultData();
          System.out.println("我是省级部门："+resultData);
        //中断广播
//        abortBroadcast();

        setResultData("我是省级部门:中央下发粮食，大家每人500斤");


    }
}
