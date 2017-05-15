package com.soul.androidcompilptions.rxandretrofi.presenter;

import com.soul.androidcompilptions.rxandretrofi.RxRetrofitActivity;
import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.bean.RestVideoBean;
import com.soul.androidcompilptions.rxandretrofi.contract.RxRetrofitContract;
import com.soul.androidcompilptions.rxandretrofi.entity.GanK;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.model.RxRetrofitModel;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.utils.Dates;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi
 * @作者：祝明
 * @描述：显示图片的控制类
 * @创建时间：2017/1/12 12:57
 */

public class RxRetrofitPresenter extends BasePresenter<RxRetrofitActivity> implements
        RxRetrofitContract.ShowNetPhotoPresenter {


    private final RxRetrofitModel mShowPhoto;
    private RxRetrofitActivity mIView;

    public RxRetrofitPresenter() {
        mShowPhoto = (RxRetrofitModel) getiModelMap().get("showPhoto");
    }

    /**
     * 加载网络数据
     *
     * @param page
     */
    @Override
    public void loadMeiZiData(int page, boolean isMoreDate) {
        mIView = getIView();

        Subscription subscribe = Observable.zip(mShowPhoto.loadMeiZiData(page),
                mShowPhoto.loadRestVideoData(page),
                this::createMeiZhiDataWithRestVideoDataDesc)
                .map(meiZhiData -> meiZhiData.results)
                .flatMap(Observable::from)
                .toSortedList((meiZhi1, meiZhi2) ->
                        meiZhi2.publishedAt.compareTo(meiZhi1.publishedAt))
                .doOnNext(this::saveMeiZhiS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiZhiData -> {
                    mIView.loadDataSucceed(meiZhiData, isMoreDate);
                }, e ->
                        mIView.loadDataFailure(e));
        addSubscription(subscribe);
    }

    private int mLastVideoIndex = 0;

    private MeiZhiBean createMeiZhiDataWithRestVideoDataDesc(MeiZhiBean data, RestVideoBean love) {
        for (MeiZhi meizhi : data.results) {
            meizhi.desc = meizhi.desc + " " +
                    getFirstVideoDesc(meizhi.publishedAt, love.results);
        }
        mLastVideoIndex=0;
        return data;
    }


    private String getFirstVideoDesc(Date publishedAt, List<GanK> results) {
        String videoDesc = "";
        for (int i = mLastVideoIndex; i < results.size(); i++) {
            GanK video = results.get(i);
            if (video.publishedAt == null)
                video.publishedAt = video.createdAt;
            if (Dates.isTheSameDay(publishedAt, video.publishedAt)) {
                videoDesc = video.desc;
                mLastVideoIndex = i;
                break;
            }
        }
        return videoDesc;
    }

    /**
     * 存储数据
     *
     * @param meiZhiS
     */
    public void saveMeiZhiS(List<MeiZhi> meiZhiS) {
        mShowPhoto.saveMeiZhiS(meiZhiS);
    }

    /**
     * 获取本地数据
     *
     * @return
     */
    public List<MeiZhi> getLocalData() {
        return mShowPhoto.getLocalData();
    }


    @Override
    public HashMap<String, IModel> getiModelMap() {

        return loadModelMap(new RxRetrofitModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("showPhoto", models[0]);
        return map;
    }
}
