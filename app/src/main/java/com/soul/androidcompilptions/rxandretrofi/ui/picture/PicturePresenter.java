package com.soul.androidcompilptions.rxandretrofi.ui.picture;

import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.ui.meizi.MeiZhiModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.ui.picture
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/6/16 17:02
 */

public class PicturePresenter extends PictureContract.Presenter {

    private int page;

    public PicturePresenter() {
        mModel = new MeiZhiModel();
    }

    public void showLocalData(int position) {
        int i = position + ((10 - position % 10));
        //获取数据
        List<MeiZhi> localData = mModel.getLocalData(i);
        page = localData.size() / 10;
        //传递数据
        mView.showLocalData(localData, position);
    }

    public void loadMoreData() {
        mModel.loadMeiZiData(++page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<MeiZhiBean>() {
                    @Override
                    public void call(MeiZhiBean meiZhiBean) {
                        mModel.saveMeiZhiS(meiZhiBean.results);
                    }
                })
                .subscribe(new Subscriber<MeiZhiBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MeiZhiBean meiZhiBean) {
                        mView.showMoreData(meiZhiBean);
                    }
                })
        ;

    }
}
