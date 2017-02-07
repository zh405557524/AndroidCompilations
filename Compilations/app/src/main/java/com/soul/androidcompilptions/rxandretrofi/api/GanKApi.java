package com.soul.androidcompilptions.rxandretrofi.api;

import com.soul.androidcompilptions.rxandretrofi.DrakeetFactory;
import com.soul.androidcompilptions.rxandretrofi.bean.GanKBean;
import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.bean.RestVideoBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.api
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 18:19
 */

public interface GankApi {

    @GET("data/福利/" + DrakeetFactory.meizhiSize + "/{page}")
    Observable<MeiZhiBean> getMeiZhiData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GanKBean> getGanKData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

    @GET("data/休息视频/" + DrakeetFactory.meizhiSize + "/{page}")
    Observable<RestVideoBean> getRestVideoData(@Path("page") int page);


}
