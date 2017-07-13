package com.soul.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

import com.soul.library.utils.LogUtils;
import com.soul.library.utils.ThreadFactory;
import com.soul.servicedemo.MusicInfo;

import java.util.List;

public class MusicService extends Service {


    private int mCurrentPosition;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("服务绑定成功");
        return new MyBinder();
    }


    public void play(final List<MusicInfo> infoS, final int index) {
        LogUtils.i("开始播放音乐" + infoS.get(index).getName());
        mCurrentPosition = index;
        ThreadFactory.getNormaPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                int newPosition = (index + 1) % infoS.size();
                play(infoS, newPosition);
            }
        });
    }


    public int getCurrentPosition(){
        return mCurrentPosition;
    }
    class MyBinder extends Binder implements IMusicService {

        @Override
        public void callPlay(List<MusicInfo> infoS, int index) {
            play(infoS, index);
        }

        @Override
        public int callGetCurrentPosition() {
            return getCurrentPosition();
        }
    }


}
