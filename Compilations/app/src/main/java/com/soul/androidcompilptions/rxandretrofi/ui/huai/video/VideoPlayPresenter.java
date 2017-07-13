package com.soul.androidcompilptions.rxandretrofi.ui.huai.video;

import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoUrlBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 23:02
 */

public class VideoPlayPresenter extends VideoPlayContract.VideoPlayPresenter {

    public VideoPlayPresenter() {
        mModel = new VideoPlayModel();
    }

    @Override
    public void loadVideoData(int course_id, int spec_id) {
        VideoBean videoBean = mModel.loadVideoLocalData(course_id, spec_id);
        if (videoBean != null && videoBean.getData() != null && videoBean.getData() != null) {
            if (mView != null) {
                mModel.saveVideoData(videoBean, videoBean.getData().getHead().getCourse_name());
                mView.loadVideoDataSucceed(videoBean);
            }
        }
        Subscription subscribe = mModel.loadVideoData(course_id, spec_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<VideoBean>() {
                    @Override
                    public void call(VideoBean videoBean) {
                        mModel.saveVideoData(videoBean, course_id);
                    }
                })
                .subscribe(new Subscriber<VideoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(VideoBean detailBean) {
                        if (mView != null) {
                            mView.loadVideoDataSucceed(detailBean);
                        }
                    }
                });

        addSubscription(subscribe);
    }

    @Override
    public void loadVideoUrlData(int course_id, int spec_id, int section_id) {
        VideoUrlBean videoUrlBean = mModel.loadVideoUrlLocalData(course_id, spec_id, section_id);
        if (videoUrlBean != null && videoUrlBean.getData() != null) {
            if (mView != null) {
                mView.loadVideoUrlSucceed(videoUrlBean);
            }
        }
        Subscription subscribe = mModel.loadVideoUrlData(course_id, spec_id, section_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<VideoUrlBean>() {
                    @Override
                    public void call(VideoUrlBean videoBean) {
                        mModel.saveVideoUrl(videoBean, section_id);
                    }
                })
                .subscribe(new Subscriber<VideoUrlBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(VideoUrlBean detailBean) {
                        if (mView != null) {
                            mView.loadVideoUrlSucceed(detailBean);
                        }
                    }
                });

        addSubscription(subscribe);
    }

    @Override
    public void loadVideoListData(int course_id, int spec_id, int teach_id) {
        AudioListBean audioListBean = mModel.loadAudioListData(course_id, spec_id, teach_id);
        if (audioListBean != null && audioListBean.getData() != null) {
            if (mView != null) {


                mView.loadVideoListDataSucceed(audioListBean);
            }
        }
        Subscription subscribe = mModel.loadVideoListData(course_id, spec_id, teach_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<AudioListBean>() {
                    @Override
                    public void call(AudioListBean audioListBean) {
                        mModel.saveAudioData(audioListBean, teach_id);
                    }
                })
                .subscribe(new Subscriber<AudioListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AudioListBean audioListBean) {
                        if (mView != null) {
                            mView.loadVideoListDataSucceed(audioListBean);
                        }
                    }
                });

        addSubscription(subscribe);
    }

    @Override
    public List<DownloadAudioBean> loadDownloadData(int section_id) {
        ArrayList<DownloadAudioBean> section_id1 = DBHelper.getInstance().query(DownloadAudioBean.class, "section_id", section_id + "");
        return section_id1;
    }

    @Override
    public void loadVideoData(String course_name) {
        VideoBean videoBean = mModel.loadVideoLocalData(course_name);
        if (videoBean != null && videoBean.getData() != null && videoBean.getData() != null) {
            if (mView != null) {
                mView.loadVideoDataSucceed(videoBean);
            }
        }
    }
}
