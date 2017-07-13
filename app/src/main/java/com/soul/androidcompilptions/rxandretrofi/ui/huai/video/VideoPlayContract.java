package com.soul.androidcompilptions.rxandretrofi.ui.huai.video;

import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoUrlBean;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IView;

import java.util.List;

import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 23:01
 */

public interface VideoPlayContract {
    interface VideoPlayView extends IView {

        /**
         * 加载视频节目清单成功
         *
         * @param detailBean
         */
        void loadVideoDataSucceed(VideoBean detailBean);

        /**
         * 获取视频地址成功
         *
         * @param detailBean
         */
        void loadVideoUrlSucceed(VideoUrlBean detailBean);

        /**
         * 加载视频列表成功
         *
         * @param audioListBean
         */
        void loadVideoListDataSucceed(AudioListBean audioListBean);
    }

    interface Model extends IModel {
        /**
         * 从网络获取视频节目清单
         *
         * @param course_id
         * @param spec_id
         */
        Observable<VideoBean> loadVideoData(int course_id, int spec_id);

        /**
         * 从网络获取视频地址
         *
         * @param course_id
         * @param spec_id
         */
        Observable<VideoUrlBean> loadVideoUrlData(int course_id, int spec_id, int section_id);

        /**
         * 从网络获取缓存列表
         *
         * @param course_id
         * @param spec_id
         */
        Observable<AudioListBean> loadVideoListData(int course_id, int spec_id, int teach_id);

        /**
         * 保存视频节目清单
         *
         * @param videoBean
         * @param course_id
         */
        void saveVideoData(VideoBean videoBean, int course_id);

        /**
         * 保存视频节目清单
         *
         * @param videoBean
         * @param course_name
         */
        void saveVideoData(VideoBean videoBean, String course_name);

        /**
         * 保存视频地址
         *
         * @param videoBean
         * @param section_id
         */
        void saveVideoUrl(VideoUrlBean videoBean, int section_id);


        /**
         * 保存视频列表数据
         *
         * @param audioListBean
         * @param course_id
         */
        void saveAudioData(AudioListBean audioListBean, int course_id);

        /**
         * 从本地 获取视频节目单
         *
         * @param course_id
         * @param spec_id
         * @return
         */
        VideoBean loadVideoLocalData(int course_id, int spec_id);

        /**
         * 从本地 获取视频节目单
         *
         * @return
         */
        VideoBean loadVideoLocalData(String courseName);


        /**
         * 从本地获取视频地址
         *
         * @param course_id
         * @param spec_id
         * @param section_id
         * @return
         */
        VideoUrlBean loadVideoUrlLocalData(int course_id, int spec_id, int section_id);

        AudioListBean loadAudioListData(int course_id, int spec_id, int teach_id);

    }

    abstract class VideoPlayPresenter extends BasePresenter<VideoPlayView, Model> {

        /**
         * 从网络获取视频节目清单
         *
         * @param course_id
         * @param spec_id
         */
        public abstract void loadVideoData(int course_id, int spec_id);


        /**
         * 从网络获取视频地址
         *
         * @param course_id
         * @param spec_id
         * @param section_id
         */
        public abstract void loadVideoUrlData(int course_id, int spec_id, int section_id);

        /**
         * 查看视频缓存列表
         *
         * @param course_id
         * @param spec_id
         */
        public abstract void loadVideoListData(int course_id, int spec_id, int teach_id);

        public abstract List<DownloadAudioBean> loadDownloadData(int section_id);

        /**
         * 获取本地数据
         *
         * @param course_name
         */
        public abstract void loadVideoData(String course_name);
    }

}
