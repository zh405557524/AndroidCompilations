package com.soul.multimediademo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 *
 */
public class SufaceActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private int mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suface);

        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);

        final SurfaceHolder holder = sv.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    //类型 推送缓冲区，不是我们自定义缓冲区，而是由omediaplayer把数据自动推送到缓冲区
                    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mMediaPlayer.setDataSource("http://192.168.10.90:8080/demo.mp4");
                    mMediaPlayer.setDisplay(holder);
                    mMediaPlayer.prepareAsync();
                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mMediaPlayer.seekTo(mCurrentPosition);
                            mMediaPlayer.start();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mCurrentPosition = mMediaPlayer.getCurrentPosition();
                }
            }
        });


    }
}
