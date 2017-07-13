package com.soul.androidcompilptions.rxandretrofi.ui.huai.audio;

import android.util.Log;

import com.soul.androidcompilptions.rxandretrofi.bean.AudioBean;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.detail.DetailModel;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/4 18:55
 */

public class AudioPresenter extends AudioContract.VoicePresenter {


    private final DetailModel mDetailModel;

    public AudioPresenter() {
        mModel = new AudioModel();
        mDetailModel = new DetailModel();
    }


    @Override
    public void loadVoiceData(String date) {
        Subscription subscribe = mModel.loadVoiceData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<AudioBean>() {
                    @Override
                    public void call(AudioBean voiceBean) {
                        mModel.saveVoiceLocalData(voiceBean, date);
                    }
                })
                .subscribe(new Subscriber<AudioBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.loadVoiceDataFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(AudioBean voiceBean) {
                        mView.loadVoiceDataSucceed(voiceBean);
                        Log.i("Tag", "voiceBean:" + voiceBean.toString());
                    }
                });
        addSubscription(subscribe);

    }

    @Override
    public AudioBean loadLocalData(String s) {
        return mModel.loadLocalData(s);
    }


}
