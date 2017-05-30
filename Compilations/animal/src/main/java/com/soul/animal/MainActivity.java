package com.soul.animal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    /**
     * 歌名
     */
    @BindView(R.id.tv_songName)
    TextView songName;

    /**
     * 歌手
     */
    @BindView(R.id.tv_singerName)
    TextView singerName;

    /**
     * 播放器时间
     */
    @BindView(R.id.ll_music_time)
    LinearLayout playTime;

    @BindView(R.id.rl_content)
    RelativeLayout mRelativeLayout;

    /**
     * 封面
     */
    @BindView(R.id.iv_player_cover)
    ImageView ivCover;


    private MusicStateAnimation mMusicStateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMusicStateAnimation = new MusicStateAnimation();
        mMusicStateAnimation.startAlphaAnimal(mMusicPlayAnimalView, 0.f, 0);
    }

    @OnClick({R.id.ll_play_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_play_info:
                //                mMusicPlayAnimalView.start();
                llPlayInfo.setClickable(false);
                mMusicStateAnimation.startAlphaAnimal(songName, 0.f, 0);
                mMusicStateAnimation.startAlphaAnimal(singerName, 0.f, 0);
                mMusicStateAnimation.startAlphaAnimal(ivCover, 0.f, 0);
                mMusicStateAnimation.startAnimationToMusicCover(this, ivCover, mMusicPlayAnimalView.getMusicCover(), mMusicPlayAnimalView);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mMusicPlayAnimalView.getVisibility() == View.VISIBLE) {
            mMusicStateAnimation.startAnimationToMainFromMusicCover(this, ivCover, mMusicPlayAnimalView.getMusicCover(), mMusicPlayAnimalView);
            llPlayInfo.setClickable(true);
            mMusicStateAnimation.startAlphaAnimal(llPlayInfo, 1.f, 0);
            mMusicStateAnimation.startAlphaAnimal(songName, 1.f, 0);
            mMusicStateAnimation.startAlphaAnimal(singerName, 1.f, 0);
            mMusicStateAnimation.startAlphaAnimal(ivCover, 1.f, 0);
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
