package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import android.text.TextUtils;

import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DetailBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.library.utils.UIUtils;

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
 * @创建时间：2017/7/6 22:22
 */

public class DetailPresenter extends DetailContract.DetailPresenter {

    public DetailPresenter() {
        mModel = new DetailModel();

    }

    /**
     * 从网络获取详情数据
     *
     * @param broadcast_id
     */
    @Override
    public void loadDetailData(String broadcast_id) {
        DetailBean detailBean = mModel.loadDetailLocalData(broadcast_id);
        if (detailBean != null && detailBean.getData() != null && detailBean.getData().getBroadcast() != null
                && !TextUtils.isEmpty(detailBean.getData().getBroadcast().getWebview_url())
                && !TextUtils.isEmpty(detailBean.getData().getBroadcast().getSubject())) {
            if (mView != null) {
                UIUtils.postTaskSafely(() -> mView.loadDetailDataSucceed(detailBean));
            }
            return;
        }
        Subscription subscribe = mModel.loadDetailData(broadcast_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<DetailBean>() {
                    @Override
                    public void call(DetailBean detailBean) {
                        mModel.saveDetail(detailBean, broadcast_id);
                    }
                })
                .subscribe(new Subscriber<DetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DetailBean detailBean) {
                        if (mView != null) {
                            mView.loadDetailDataSucceed(detailBean);
                        }
                    }
                });

        addSubscription(subscribe);

    }

    /**
     * 从网络获取音频集合数据
     *
     * @param audio_id
     */
    @Override
    public void loadAudioListData(String audio_id) {
        AudioListBean audioListBean = mModel.loadAudioLocalData(audio_id);
        if (audioListBean != null && audioListBean.getData() != null
                && audioListBean.getData().getHead() != null
                && audioListBean.getData().getList() != null
                && audioListBean.getData().getList().size() > 0) {
            if (mView != null) {
                mView.loadAudioListDataSucceed(audioListBean);
            }
        }

        Subscription subscribe = mModel.loadAudioListData(audio_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<AudioListBean>() {
                    @Override
                    public void call(AudioListBean audioListBean) {
                        mModel.saveAudio(audioListBean, audio_id);
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
                        mView.loadAudioListDataSucceed(audioListBean);
                    }
                });

        addSubscription(subscribe);
    }



    @Override
    public DownloadAudioBean saveDownloadRecord(String url, int section_id, AudioListBean.DataBean.HeadBean video_type,
                                                String title, String time, AudioListBean.DataBean.ListBean listBean) {
        return mModel.saveDownloadRecord(url, section_id, video_type, title, time,listBean);
    }


    /**
     * 获取音频所有的数据
     *
     * @param video_type
     * @return
     */
    @Override
    List<DownloadAudioBean> getDownloadRecord(String video_type) {

        return mModel.getDownloadRecord(video_type);
    }

    /**
     * 获取正在下载的数据
     */
    @Override
    public List<DownloadAudioBean> loadDownloadData() {

        return mModel.loadDownloadData();
    }

    @Override
    public List<DownloadAudioBean> getLocalData(int broadcast_id) {
        ArrayList<DownloadAudioBean> section_id = DBHelper.getInstance().query(DownloadAudioBean.class, "section_id", broadcast_id + "");
        return section_id;
    }


}
