package com.soul.androidcompilptions.rxandretrofi.ui.meizi;

import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.bean.RestVideoBean;
import com.soul.androidcompilptions.rxandretrofi.entity.GanK;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.library.utils.Dates;

import java.util.Date;
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

public class MeiZhiPresenter extends MeiZhiContract.ShowNetPhotoPresenter {


    private int page;

    public MeiZhiPresenter() {
        mModel = new MeiZhiModel();
    }

    /**
     * 加载网络数据
     *
     * @param page
     */
    @Override
    public void loadMeiZiData(int page, boolean isMoreDate) {
        Subscription subscribe = Observable.zip(mModel.loadMeiZiData(page),
                mModel.loadRestVideoData(page),
                this::createMeiZhiDataWithRestVideoDataDesc)
                .map(meiZhiData -> meiZhiData.results)
                .flatMap(Observable::from)
                .toSortedList((meiZhi1, meiZhi2) ->
                        meiZhi2.publishedAt.compareTo(meiZhi1.publishedAt))
                .doOnNext(this::saveMeiZhiS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiZhiData -> {
                    mView.loadDataSucceed(meiZhiData, isMoreDate);
                }, e ->
                        mView.loadDataFailure(e));
        addSubscription(subscribe);
    }

    private int mLastVideoIndex = 0;

    private MeiZhiBean createMeiZhiDataWithRestVideoDataDesc(MeiZhiBean data, RestVideoBean love) {
        for (MeiZhi meizhi : data.results) {
            meizhi.desc = meizhi.desc + " " +
                    getFirstVideoDesc(meizhi.publishedAt, love.results);
        }
        mLastVideoIndex = 0;
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


    public void showLocalData(int position) {
        int i = position + ((10 - position % 10));
        //获取数据
        List<MeiZhi> localData = mModel.getLocalData(i);
        page = localData.size() / 10;
        //传递数据
        mView.showLocalData(localData, position);
    }

    /**
     * 存储数据
     *
     * @param meiZhiS
     */
    public void saveMeiZhiS(List<MeiZhi> meiZhiS) {
        mModel.saveMeiZhiS(meiZhiS);
    }

    /**
     * 获取本地数据
     *
     * @return
     */
    public List<MeiZhi> getLocalData() {
        return mModel.getLocalData();
    }
}
