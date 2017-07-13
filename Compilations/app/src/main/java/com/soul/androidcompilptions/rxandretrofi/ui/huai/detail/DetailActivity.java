package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DetailBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.androidcompilptions.rxandretrofi.config.ActionConstant;
import com.soul.library.base.BaseAdapter;
import com.soul.library.base.BaseRxActivity;
import com.soul.library.utils.LogUtils;
import com.soul.library.utils.ThreadFactory;
import com.soul.library.utils.UIUtils;
import com.soul.library.widget.photoview.PhotoView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseRxActivity<DetailPresenter> implements DetailContract.DetailView {

    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_title2) TextView tvTitle2;
    @BindView(R.id.iv_head) ImageView ivHead;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.play_bar) SeekBar playBar;
    @BindView(R.id.tv_played) TextView tvPlayed;
    @BindView(R.id.iv_play) ImageView ivPlay;
    @BindView(R.id.tv_duration) TextView tvDuration;
    @BindView(R.id.rv_photos) RecyclerView rvPhotos;
    @BindView(R.id.root_detail) View rootView;
    private DownloadPopupWindow mDownloadPopupWindow;

    private MediaPlayer mMediaPlayer;

    private TimerTask mTimerTask;
    private Timer mTimer;
    private int mBroadcast_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected DetailPresenter loadPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void initView() {
        mDownloadPopupWindow = new DownloadPopupWindow(this, itemOnClick);
        mDownloadPopupWindow.setClippingEnabled(false);
    }

    @Override
    protected void initData() {
        mBroadcast_id = getIntent().getIntExtra(ActionConstant.EXTRA_BROADCAST_ID, 0);
        mPresenter.loadDetailData(mBroadcast_id + "");
    }

    @Override
    protected void initEvent() {
        playBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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


    @OnClick({R.id.iv_back, R.id.tv_download})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_download:
                mPresenter.loadAudioListData(mBroadcast_id + "");
                break;
        }
    }


    @Override
    public void loadAudioListDataSucceed(AudioListBean detailBean) {
        if (detailBean != null) {
            mDownloadPopupWindow.setData(detailBean, mPresenter);
            mDownloadPopupWindow.setHeight(rootView.getHeight());
            mDownloadPopupWindow.showAtLocation(rootView, Gravity.TOP, 0, UIUtils.getStatusBarHeight());
            LogUtils.i(detailBean.toString());
        }
    }

    @Override
    public void loadAudioListFailure(String sr) {

    }

    @Override
    public void loadDetailDataSucceed(DetailBean detailBean) {
        DetailBean.DataBean data = detailBean.getData();
        DetailBean.DataBean.BroadcastBean broadcast = data.getBroadcast();
        tvTitle.setText(broadcast.getSubject());
        tvTitle2.setText(broadcast.getSubject());
        ThreadFactory.getNormaPool().execute(() -> {
            List<DownloadAudioBean> localData = mPresenter.getLocalData(mBroadcast_id);
            if (localData != null && localData.size() > 0 && !TextUtils.isEmpty(localData.get(0).getLocalUrl())) {
                play(localData.get(0).getLocalUrl());
            } else {
                play(broadcast.getWebview_url());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPhotos.setLayoutManager(linearLayoutManager);
        rvPhotos.setAdapter(new BaseAdapter<String>(mContext, R.layout.item_pricture, broadcast.getPhotos()) {
            @Override
            protected void convert(ViewHolder viewHolder, String url, int i) {
                PhotoView view = viewHolder.getView(R.id.iv_photo);
                Glide.with(mContext)
                        .load(url)
                        .centerCrop()
                        .into(view);
            }
        });
    }

    @Override
    public void loadDetailFailure(String sr) {

    }


    private View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        }
    };


    public void play(String url) {
        try {

            //创建一个媒体播放器
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//初始化内存空间
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();//准备
            mMediaPlayer.start();


            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    playBar.setMax(mMediaPlayer.getDuration());
                    playBar.setProgress(mMediaPlayer.getCurrentPosition());
                }
            };
            mTimer.schedule(mTimerTask, 0, 500);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
