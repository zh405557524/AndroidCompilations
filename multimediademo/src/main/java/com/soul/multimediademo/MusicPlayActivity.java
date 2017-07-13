package com.soul.multimediademo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.soul.multimediademo.R.id.sb;

/**
 * 音乐播放 常见api
 */
public class MusicPlayActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private SeekBar mSb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        mSb = (SeekBar) findViewById(sb);
        mSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //设置进度
                if (mMediaPlayer != null) {
                    mMediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });


    }

    public void play(View view) {
        try {

            //创建一个媒体播放器
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//初始化内存空间
            mMediaPlayer.setDataSource("http://192.168.0.106:8080/aini.mp3");
            mMediaPlayer.prepare();//准备
            mMediaPlayer.start();


            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    mSb.setMax(mMediaPlayer.getDuration());
                    mSb.setProgress(mMediaPlayer.getCurrentPosition());
                }
            };
            timer.schedule(timerTask, 0, 500);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(View view) {
        if (mMediaPlayer != null) {

            mMediaPlayer.pause();
        }
    }

    public void resume(View view) {
        if (mMediaPlayer != null) {

            mMediaPlayer.start();
        }
    }

    public void replay(View view) {
        if (mMediaPlayer != null) {

            mMediaPlayer.seekTo(0);
        }
    }

    public void stop(View view) {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            //            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }


}
