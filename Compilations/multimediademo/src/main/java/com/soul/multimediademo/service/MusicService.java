package com.soul.multimediademo.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.soul.multimediademo.MusicInfo;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {


    private int mCurrentPosition;
    private MediaPlayer mMediaPlayer;

    public MusicService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
//        LogUtils.i("服务绑定成功");
        return new MyBinder();
    }


    public void play(final List<MusicInfo> infoS, final int index) {
//        LogUtils.i("开始播放音乐" + infoS.get(index).getName());
        mCurrentPosition = index;

        try {
            mMediaPlayer.stop();
            mMediaPlayer.reset();//恢复初始状态
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(infoS.get(index).getPath());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //切换下一个音乐，列表循环播放

                int newPosition = (index+1)&infoS.size();
                    play(infoS,newPosition);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
