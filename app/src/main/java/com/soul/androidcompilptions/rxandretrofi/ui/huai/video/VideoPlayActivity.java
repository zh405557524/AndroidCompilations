package com.soul.androidcompilptions.rxandretrofi.ui.huai.video;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.jungle.mediaplayer.base.VideoInfo;
import com.jungle.mediaplayer.widgets.JungleMediaPlayer;
import com.jungle.mediaplayer.widgets.SimpleJungleMediaPlayerListener;
import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoUrlBean;
import com.soul.androidcompilptions.rxandretrofi.config.ActionConstant;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.detail.DetailPresenter;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.detail.DownloadPopupWindow;
import com.soul.library.base.BaseAdapter;
import com.soul.library.base.BaseRxActivity;
import com.soul.library.utils.FileHelp;
import com.soul.library.utils.LogUtils;
import com.soul.library.utils.UIUtils;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoPlayActivity extends BaseRxActivity<VideoPlayPresenter> implements
        VideoPlayContract.VideoPlayView {


    @BindView(R.id.media_player) JungleMediaPlayer mMediaPlayer;
    @BindView(R.id.rv_video_list) RecyclerView rvVideoList;
    @BindView(R.id.video_root) RelativeLayout rootView;
    private int mCourse_id;
    private int mSpec_id;
    private int mVideoZoneNormalHeight = 0;
    private boolean mIsFullScreenNow = false;
    private String mVideoUrl;

    private DownloadPopupWindow mDownloadPopupWindow;
    private VideoBean mVideoBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected VideoPlayPresenter loadPresenter() {
        return new VideoPlayPresenter();
    }

    @Override
    protected void initView() {
        rvVideoList.setLayoutManager(new LinearLayoutManager(mContext));
        mDownloadPopupWindow = new DownloadPopupWindow(this, itemOnClick);
        mDownloadPopupWindow.setClippingEnabled(false);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mCourse_id = intent.getIntExtra(ActionConstant.EXTRA_COURSE_ID, 0);
        mSpec_id = intent.getIntExtra(ActionConstant.EXTRA_SPEC_ID, 0);

        if (mCourse_id != 0) {
            mPresenter.loadVideoData(mCourse_id, mSpec_id);
        } else {
            String  course_name = intent.getStringExtra(ActionConstant.EXTRA_COURSE_NAME);
            mPresenter.loadVideoData(course_name);
        }

        initMediaPlayer();
    }

    @Override
    protected void initEvent() {

    }


    @OnClick({R.id.player_back_zone, R.id.tv_download})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.player_back_zone:
                finish();
                break;
            case R.id.tv_download:
                //查看视频缓存列表
                if (mVideoBean != null) {
                    mPresenter.loadVideoListData(mCourse_id, mSpec_id, mVideoBean.getData().getHead().getTeach_id());
                }
                break;
        }

    }


    private View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        }
    };

    @Override
    public void loadVideoDataSucceed(VideoBean detailBean) {
        LogUtils.i(detailBean.toString());
        mVideoBean = detailBean;
        if (detailBean != null && detailBean.getData() != null) {
            List<DownloadAudioBean> downloadAudioBeans = mPresenter.loadDownloadData(detailBean.getData().getList().get(0).getSections().get(0).getSection_id());
            if (downloadAudioBeans != null && downloadAudioBeans.size() > 0 && !TextUtils.isEmpty(downloadAudioBeans.get(0).getLocalUrl())) {
                String localUrl = downloadAudioBeans.get(0).getLocalUrl();
                mMediaPlayer.stop();
                mMediaPlayer.playMedia(new VideoInfo(localUrl));
            } else {
                mPresenter.loadVideoUrlData(mCourse_id, mSpec_id, detailBean.getData().getList().get(0).getSections().get(0).getSection_id());
            }
        }

        List<VideoBean.DataBean.ListBean.SectionsBean> data = new ArrayList<>();

        List<VideoBean.DataBean.ListBean> list = detailBean.getData().getList();
        for (VideoBean.DataBean.ListBean listBean : list) {
            data.addAll(listBean.getSections());
        }
        rvVideoList.setAdapter(new BaseAdapter<VideoBean.DataBean.ListBean.SectionsBean>(mContext, R.layout.item_video, data) {

            @Override
            protected void convert(ViewHolder viewHolder, VideoBean.DataBean.ListBean.SectionsBean sectionsBean, int i) {
                viewHolder.setText(R.id.tv_title, sectionsBean.getSection_name());
                viewHolder.setText(R.id.tv_time, FileHelp.formatSize(sectionsBean.getDuration()));
                viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<DownloadAudioBean> downloadAudioBeans = mPresenter.loadDownloadData(sectionsBean.getSection_id());
                        if (downloadAudioBeans != null && downloadAudioBeans.size() > 0 && !TextUtils.isEmpty(downloadAudioBeans.get(0).getLocalUrl())) {
                            String localUrl = downloadAudioBeans.get(0).getLocalUrl();
                            mMediaPlayer.stop();
                            mMediaPlayer.playMedia(new VideoInfo(localUrl));
                        } else {
                            mPresenter.loadVideoUrlData(mCourse_id, mSpec_id, sectionsBean.getSection_id());
                        }
                    }
                });
            }
        });

    }

    @Override
    public void loadVideoUrlSucceed(VideoUrlBean detailBean) {
        LogUtils.i(detailBean.toString());
        if (detailBean != null && detailBean.getData() != null && !TextUtils.isEmpty(detailBean.getData().getUrl())) {
            mVideoUrl = detailBean.getData().getUrl();
            mMediaPlayer.stop();
            mMediaPlayer.playMedia(new VideoInfo(mVideoUrl));
        }
    }

    @Override
    public void loadVideoListDataSucceed(AudioListBean audioListBean) {
        if (audioListBean != null) {
            mDownloadPopupWindow.setData(audioListBean, new DetailPresenter());
            mDownloadPopupWindow.setHeight(rootView.getHeight());
            mDownloadPopupWindow.showAtLocation(rootView, Gravity.TOP, 0, UIUtils.getStatusBarHeight());
            LogUtils.i(audioListBean.toString());
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            switchVideoContainer(true);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            switchVideoContainer(false);
        }
    }


    private void initMediaPlayer() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mVideoZoneNormalHeight = (int) (metrics.widthPixels / 1.77f);

        FrameLayout panel = (FrameLayout) findViewById(R.id.adjust_panel_container);
        mMediaPlayer = (JungleMediaPlayer) findViewById(R.id.media_player);
        mMediaPlayer.setAdjustPanelContainer(panel);
        mMediaPlayer.setAutoReloadWhenError(false);
        mMediaPlayer.setPlayerListener(new SimpleJungleMediaPlayerListener() {

            @Override
            public void onTitleBackClicked() {
                if (mMediaPlayer.isFullscreen()) {
                    mMediaPlayer.switchFullScreen(false);
                    return;
                }

                finish();
            }
        });
    }

    private void switchVideoContainer(boolean fullScreen) {
        if (mIsFullScreenNow == fullScreen) {
            return;
        }

        mIsFullScreenNow = fullScreen;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (mIsFullScreenNow) {
                actionBar.hide();
            } else {
                actionBar.show();
            }
        }

        updateVideoZoneSize(fullScreen);
    }

    private void updateVideoZoneSize(final boolean fullScreen) {
        ViewGroup.LayoutParams params = mMediaPlayer.getLayoutParams();
        params.height = fullScreen
                ? ViewGroup.LayoutParams.MATCH_PARENT
                : mVideoZoneNormalHeight;
        mMediaPlayer.setLayoutParams(params);
    }


    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.destroy();
    }
}