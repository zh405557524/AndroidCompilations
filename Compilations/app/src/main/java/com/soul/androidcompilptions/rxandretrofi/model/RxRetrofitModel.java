package com.soul.androidcompilptions.rxandretrofi.model;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.soul.androidcompilptions.rxandretrofi.bean.GanKBean;
import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.bean.RestVideoBean;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static com.soul.library.BaseApplication.sDb;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 18:10
 */

public class RxRetrofitModel extends GanKBaseModel {

    public Observable<MeiZhiBean> loadMeiZiData(int page) {
        return sGankIO.getMeiZhiData(page);
    }

    public Observable<RestVideoBean> loadRestVideoData(int page){

        return sGankIO.getRestVideoData(page);
    }

    public Observable<GanKBean> loadGanKData(int year,int month,int day){

        return sGankIO.getGanKData(year,month,day);
    }

    /**
     * 存储数据
     *
     * @param meiZhiS
     */
    public void saveMeiZhiS(List<MeiZhi> meiZhiS) {
        outer:
        for (MeiZhi meiZhi : meiZhiS) {
            ArrayList<MeiZhi> query = sDb.query(MeiZhi.class);
            if (query != null && !query.isEmpty()) {
                for (MeiZhi meiZhi1 : query) {
                    if (meiZhi.publishedAt.equals(meiZhi1.publishedAt)) {
                        continue outer;
                    }
                }
                sDb.save(meiZhi);
            } else {
                sDb.insert(meiZhi);
            }
        }
    }

    /**
     * 获取本地的数据
     *
     * @return
     */
    public List<MeiZhi> getLocalData() {

        QueryBuilder query = new QueryBuilder(MeiZhi.class);
        query.appendOrderDescBy("publishedAt");
        query.limit(0, 10);
        ArrayList query1 = sDb.query(query);
        return query1;
    }


}

