package com.soul.androidcompilptions.rxandretrofi.api;

import com.soul.androidcompilptions.rxandretrofi.config.ServerManager;
import com.soul.library.http.DrakeetRetrofit;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 18:14
 */

public class DrakeetFactory {
    protected static final Object monitor = new Object();
    static GankApi sGankIOSingleton = null;
    public static final int meizhiSize = 10;
    private static BadBoyApi sBadBoyApi;
    private static BadBoyApi sBadBoyResourceApi;

    public static GankApi getGankIOSingleton() {

        synchronized (monitor) {
            if (sGankIOSingleton == null) {
                sGankIOSingleton = new DrakeetRetrofit(ServerManager.getServerUrl(""), null).mGankRest.create(GankApi.class);
            }
            return sGankIOSingleton;
        }
    }

    public static BadBoyApi getBadBoy() {

        synchronized (monitor) {
            if (sBadBoyApi == null) {
                sBadBoyApi = new DrakeetRetrofit(ServerManager.SERVER_BAD_BOY, DrakeetRetrofit.addQueryParameterInterceptor()).mGankRest.create(BadBoyApi.class);
            }
            return sBadBoyApi;
        }
    }

    public static BadBoyApi getBadBoyResource() {

        synchronized (monitor) {
            if (sBadBoyResourceApi == null) {
                sBadBoyResourceApi = new DrakeetRetrofit(ServerManager.SERVER_BAD_BOY_RESOURCE, DrakeetRetrofit.addQueryParameterInterceptor()).mGankRest.create(BadBoyApi.class);
            }
            return sBadBoyResourceApi;
        }
    }


}
