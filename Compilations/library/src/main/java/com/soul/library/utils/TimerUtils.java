package com.soul.library.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.library
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/2/22 11:18
 */

public class TimerUtils {

    static int count1;
    static int count2;

    public static void runTime() {
        count1 = 0;
        final Timer[] timer = {new Timer()};
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count1++;
                sendMessageListener();
                if (count1 == 5) {
                    count2++;
                    timer[0].cancel();
                    timer[0] = null;
                    if (count2 == 5) {
                        //结束
                    } else {
                        runTime();
                    }
                }
            }
        };
        timer[0].schedule(timerTask, 5000);
    }


    public static void sendMessageListener() {

    }


}
