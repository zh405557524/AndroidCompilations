package com.soul.androidcompilptions.rxandretrofi.contract;

import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;

import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.contract
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 12:59
 */

public class RxRetrofitContract {

    public interface ShowNetPhotoView {
        /**
         * 获取服务的数据
         */
        void loadMeiZiData();

        /**
         * 加载数据成功
         *
         * @param meiZhiBean
         */
        void loadDataSucceed(List<MeiZhi> meiZhiBean,boolean isMoreDate);

        /**
         * 加载数据失败
         *
         * @param e
         */
        void loadDataFailure(Throwable e);
    }

    public interface ShowNetPhotoPresenter {
        /**
         * 获取服务的数据
         */
        void loadMeiZiData(int page, boolean isMoreDate);

        /**
         * 获取本地的数据
         *
         * @return
         */
        List<MeiZhi> getLocalData();

        /**
         * 存储本地数据
         *
         * @param data
         */
        void saveMeiZhiS(List<MeiZhi> data);
    }

}
