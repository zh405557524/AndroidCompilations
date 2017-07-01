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

    public static GankApi getGankIOSingleton() {

        synchronized (monitor) {
            if (sGankIOSingleton == null) {
                sGankIOSingleton = new DrakeetRetrofit(ServerManager.getServerUrl("")).mGankRest.create(GankApi.class);
            }
            return sGankIOSingleton;
        }
    }


}
