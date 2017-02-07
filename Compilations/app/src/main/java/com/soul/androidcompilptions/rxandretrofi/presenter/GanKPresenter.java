package com.soul.androidcompilptions.rxandretrofi.presenter;

import com.soul.androidcompilptions.rxandretrofi.bean.GanKBean;
import com.soul.androidcompilptions.rxandretrofi.contract.GanKContract;
import com.soul.androidcompilptions.rxandretrofi.entity.GanK;
import com.soul.androidcompilptions.rxandretrofi.model.RxRetrofitModel;
import com.soul.androidcompilptions.rxandretrofi.ui.GanKFragment;
import com.soul.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public class GanKPresenter extends BasePresenter<GanKFragment> implements GanKContract.GanKPresenter {

    private List<GanK> mGanKList;
    private final RxRetrofitModel mGanKModel;

    public GanKPresenter() {
        mGanKModel = (RxRetrofitModel) getiModelMap().get("ganKData");
    }

    @Override
    public void loadGanKData(int year, int month, int day) {
        GanKFragment iView = getIView();
        mGanKModel.loadGanKData(year, month, day)
                .map(data -> data.results)
                .map(this::addAllResults)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ganKList -> iView.loadDataSucceed(ganKList), Throwable::printStackTrace);
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

    @Override
    public HashMap<String, IModel> getiModelMap() {
        return loadModelMap(new RxRetrofitModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("ganKData", models[0]);

        return map;
    }

}
