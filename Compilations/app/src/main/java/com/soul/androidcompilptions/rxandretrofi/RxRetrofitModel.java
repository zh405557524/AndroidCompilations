package com.soul.androidcompilptions.rxandretrofi;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.soul.androidcompilptions.rxandretrofi.api.DrakeetFactory;
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

public class RxRetrofitModel implements RxRetrofitContract.Model {

    public Observable<MeiZhiBean> loadMeiZiData(int page) {
        return DrakeetFactory.getGankIOSingleton().getMeiZhiData(page);
    }

    public Observable<RestVideoBean> loadRestVideoData(int page) {

        return DrakeetFactory.getGankIOSingleton().getRestVideoData(page);
    }

    public Observable<GanKBean> loadGanKData(int year, int month, int day) {

        return DrakeetFactory.getGankIOSingleton().getGanKData(year, month, day);
    }

    /**
     * 存储数据
     *
     * @param meiZhiS
     */
    public void saveMeiZhiS(List<MeiZhi> meiZhiS) {
        for (MeiZhi meizhi : meiZhiS) {
            DBHelper.getInstance().save(MeiZhi.class, meizhi, "url", meizhi.url);
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

    /**
     * 获取本地的数据
     *
     * @return
     */
    public List<MeiZhi> getLocalData(int position) {
        QueryBuilder query = new QueryBuilder(MeiZhi.class);
        query.appendOrderDescBy("publishedAt");
        query.limit(0, position);
        ArrayList query1 = sDb.query(query);
        return query1;
    }


}

