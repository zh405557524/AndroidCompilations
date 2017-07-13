package com.soul.androidcompilptions.rxandretrofi.ui.huai.cache;

import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IView;

import java.util.List;

import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/11 22:00
 */

public interface MyCacheContract {

    interface MyCacheView extends IView {


        /**
         * 加载本地数据成功
         *
         * @param audioListBean
         */
        void loadDownCacheSucceed(List<List<DownloadAudioBean>> audioListBean);

    }

    interface Model extends IModel {

        /**
         * 获取缓存数据
         *
         * @return
         */
        Observable<List<DownloadAudioBean>> loadCacheData();


        /**
         * 给缓存数据进行排序
         *
         * @param list
         * @return
         */
        Observable<List<List<DownloadAudioBean>>> sortCacheData(List<DownloadAudioBean> list);
    }

    abstract class MyCachePresenter extends BasePresenter<MyCacheView, Model> {

        /**
         * 获取缓存数据
         */
        public abstract void loadCacheData();
    }

}
