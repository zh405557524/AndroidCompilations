package com.soul.androidcompilptions.rxandretrofi.ui.gan;

import com.soul.androidcompilptions.rxandretrofi.RxRetrofitModel;
import com.soul.androidcompilptions.rxandretrofi.bean.GanKBean;
import com.soul.androidcompilptions.rxandretrofi.entity.GanK;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/19 15:41
 */
public class GanKPresenter extends GanKContract.GanKPresenter {

    private List<GanK> mGanKList;

    public GanKPresenter() {
        mModel =  new RxRetrofitModel();
    }

    @Override
    public void loadGanKData(int year, int month, int day) {
        mModel.loadGanKData(year, month, day)

                .map(data -> data.results)

                .map(this::addAllResults)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GanK>>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {


                                   //                                   Throwable::printStackTrace;
                               }

                               @Override
                               public void onNext(List<GanK> ganKList) {
                                   mView.loadDataSucceed(ganKList);
                               }
                           }
                );


        mModel.loadGanKData(year, month, day)

                .map(data -> data.results)

                .map(this::addAllResults)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data ->
                                mView.loadDataSucceed(data),
                        Throwable::printStackTrace);
    }

    private List<GanK> addAllResults(GanKBean.Result result) {
        mGanKList = new ArrayList<>();
        if (result.androidList != null)
            mGanKList.addAll(result.androidList);
        if (result.restVideoList != null)
            mGanKList.addAll(result.restVideoList);
        if (result.iOSList != null)
            mGanKList.addAll(result.iOSList);
        if (result.welfareList != null)
            mGanKList.addAll(result.welfareList);
        if (result.extendResourcesList != null)
            mGanKList.addAll(result.extendResourcesList);
        if (result.blindRecommendList != null)
            mGanKList.addAll(result.blindRecommendList);
        if (result.appList != null)
            mGanKList.addAll(result.appList);
        return mGanKList;
    }

}
