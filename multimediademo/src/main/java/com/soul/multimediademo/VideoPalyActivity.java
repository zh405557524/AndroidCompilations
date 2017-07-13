package com.soul.multimediademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class VideoPalyActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_paly);
        mVideoView = (VideoView) findViewById(R.id.vv);
        mVideoView.setVideoPath("http://192.168.10.90:8080/demo.mp4");
        mVideoView.start();

    }

}
