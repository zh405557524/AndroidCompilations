package com.soul.androidcompilptions.rxandretrofi.ui.meizi;

import com.soul.androidcompilptions.rxandretrofi.bean.GanKBean;
import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.bean.RestVideoBean;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IView;

import java.util.List;

import rx.Observable;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.contract
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 12:59
 */

public interface MeiZhiContract {

    interface ShowNetPhotoView extends IView {
        /**
         * 获取服务的数据
         */
        void loadMeiZiData();

        /**
         * 加载数据成功
         *
         * @param meiZhiBean
         */
        void loadDataSucceed(List<MeiZhi> meiZhiBean, boolean isMoreDate);

        /**
         * 加载数据失败
         *
         * @param e
         */
        void loadDataFailure(Throwable e);

        void showLocalData(List<MeiZhi> localData, int position);
    }

    interface Model extends IModel {
        public Observable<MeiZhiBean> loadMeiZiData(int page);

        Observable<RestVideoBean> loadRestVideoData(int page);

        Observable<GanKBean> loadGanKData(int year, int month, int day);

        void saveMeiZhiS(List<MeiZhi> meiZhiS);

        List<MeiZhi> getLocalData();

        List<MeiZhi> getLocalData(int position);


    }

    abstract class ShowNetPhotoPresenter extends BasePresenter<ShowNetPhotoView, Model> {
        /**
         * 获取服务的数据
         */
        abstract void loadMeiZiData(int page, boolean isMoreDate);

    }

}
