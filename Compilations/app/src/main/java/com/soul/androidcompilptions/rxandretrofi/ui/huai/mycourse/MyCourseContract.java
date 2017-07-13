package com.soul.androidcompilptions.rxandretrofi.ui.huai.mycourse;

import com.soul.androidcompilptions.rxandretrofi.bean.MyCourseBean;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IView;

import rx.Observable;


/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 0:22
 */

public interface MyCourseContract {
    interface MyCourseView extends IView {

        /**
         * 加载课程成功
         *
         * @param detailBean
         */
        void loadMyCourseDataSucceed(MyCourseBean detailBean);
    }

    interface Model extends IModel {
        /**
         * 获取我的视频课程
         */
        Observable<MyCourseBean> loadMyCourseData();

        /**
         * 从本地获取课程数据
         *
         * @return
         */
        MyCourseBean loadMyCourseLocalData();

        /**
         * 将课程的网络数据保存到本地
         * @param detailBean
         */
        void saveMyCourse(MyCourseBean detailBean);
    }

    abstract class MyCoursePresenter extends BasePresenter<MyCourseView, Model> {

        /**
         * 获取我的视频课程
         */
        public abstract void loadMyCourseData();
    }

}
