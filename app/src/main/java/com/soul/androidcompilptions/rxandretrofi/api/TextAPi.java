package com.soul.androidcompilptions.rxandretrofi.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/11/15 17:38
 */

public interface TextAPi {


    /**
     * 获取音频数据
     *
     * @return
     */

    @GET("otn")
    Observable<String> getTextData();
}
