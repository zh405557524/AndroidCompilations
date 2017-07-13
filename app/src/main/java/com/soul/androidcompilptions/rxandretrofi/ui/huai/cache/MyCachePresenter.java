package com.soul.androidcompilptions.rxandretrofi.ui.huai.cache;

import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/11 22:00
 */

public class MyCachePresenter extends MyCacheContract.MyCachePresenter {


    public MyCachePresenter() {
        mModel = new MyCacheModel();
    }

    @Override
    public void loadCacheData() {
        Subscription subscribe = mModel.loadCacheData()
                .flatMap(list -> mModel.sortCacheData(list))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<List<DownloadAudioBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<List<DownloadAudioBean>> audioListBean) {
                        mView.loadDownCacheSucceed(audioListBean);
                    }
                });

        addSubscription(subscribe);

    }
}
