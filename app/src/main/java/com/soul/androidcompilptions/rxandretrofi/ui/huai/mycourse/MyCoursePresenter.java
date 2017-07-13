package com.soul.androidcompilptions.rxandretrofi.ui.huai.mycourse;

import com.soul.androidcompilptions.rxandretrofi.bean.MyCourseBean;
import com.soul.library.utils.UIUtils;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 0:23
 */

public class MyCoursePresenter extends MyCourseContract.MyCoursePresenter {


    public MyCoursePresenter() {
        mModel = new MyCourseModel();

    }

    /**
     * 从网络获取我的课程
     */
    @Override
    public void loadMyCourseData() {

        MyCourseBean detailBean = mModel.loadMyCourseLocalData();
        if (detailBean != null && detailBean.getData() != null && detailBean.getData().getList().size() > 0) {
            if (mView != null) {
                UIUtils.postTaskSafely(() -> mView.loadMyCourseDataSucceed(detailBean));
            }
            return;
        }
        Subscription subscribe = mModel.loadMyCourseData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<MyCourseBean>() {
                    @Override
                    public void call(MyCourseBean detailBean) {
                        mModel.saveMyCourse(detailBean);
                    }
                })
                .subscribe(new Subscriber<MyCourseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MyCourseBean detailBean) {
                        if (mView != null) {
                            mView.loadMyCourseDataSucceed(detailBean);
                        }
                    }
                });

        addSubscription(subscribe);

    }
}
