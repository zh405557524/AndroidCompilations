package com.soul.animal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.soul.animal.animal.MusicStateAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.musicPlayAnimal)
    MusicPlayAnimalView mMusicPlayAnimalView;

    @BindView(R.id.ll_play_info)
    LinearLayout llPlayInfo;

    @BindView(R.id.ll_songInfo)
    LinearLayout songInfo;

    @BindView(R.id.rl_content)
    RelativeLayout mRelativeLayout;

    @BindView(R.id.iv_cover)
    ImageView ivCover;


    private MusicStateAnimation mMusicStateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMusicPlayAnimalView.setVisibility(View.GONE);
        mMusicStateAnimation = new MusicStateAnimation();
        mMusicStateAnimation.startAnimationToMainFromMusicCover(mMusicPlayAnimalView.getMusicCover(), mMusicPlayAnimalView, songInfo);
    }

    @OnClick({R.id.ll_play_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_play_info:
                mMusicPlayAnimalView.setVisibility(View.VISIBLE);
                mMusicPlayAnimalView.start();
                mMusicStateAnimation.startAnimationToMusicCover(mMusicPlayAnimalView.getMusicCover(), mMusicPlayAnimalView, songInfo);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mMusicPlayAnimalView.getVisibility() == View.VISIBLE) {
            mMusicStateAnimation.startAnimationToMainFromMusicCover(mMusicPlayAnimalView.getMusicCover(), mMusicPlayAnimalView, songInfo);
            mMusicPlayAnimalView.stop();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMusicPlayAnimalView != null) {

            mMusicPlayAnimalView.onDestroy();

        }
    }
}
