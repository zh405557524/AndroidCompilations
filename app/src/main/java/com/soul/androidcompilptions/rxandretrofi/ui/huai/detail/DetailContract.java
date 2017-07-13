package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DetailBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IView;

import java.util.List;

import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/6 22:22
 */

public interface DetailContract {

    interface DetailView extends IView {


        /**
         * 获取音频数据集合
         *
         * @param detailBean
         */
        void loadAudioListDataSucceed(AudioListBean detailBean);


        /**
         * 获取音频数据失败
         *
         * @param sr
         */
        void loadAudioListFailure(String sr);

        /**
         * 获取详情界面成功
         *
         * @param detailBean
         */
        void loadDetailDataSucceed(DetailBean detailBean);

        /**
         * 获取详情界面失败
         *
         * @param sr
         */
        void loadDetailFailure(String sr);


    }

    interface Model extends IModel {
        /**
         * 网络获取详情界面
         *
         * @param broadcast_id
         * @return
         */
        Observable<DetailBean> loadDetailData(String broadcast_id);

        /**
         * 从网络获取音频集合数据
         *
         * @param audio_id
         * @return
         */
        Observable<AudioListBean> loadAudioListData(String audio_id);


        /**
         * 保存详情数据到本地
         *
         * @param detailBean
         * @param broadcast_id
         */
        void saveDetail(DetailBean detailBean, String broadcast_id);

        /**
         * 保存音频集合数据到本地
         *
         * @param audioListBean
         * @param audio_id
         */
        void saveAudio(AudioListBean audioListBean, String audio_id);


        /**
         * 获取详情界面的本地数据
         *
         * @param broadcast_id
         * @return
         */
        DetailBean loadDetailLocalData(String broadcast_id);


        /**
         * 获取音频集合的本地数据
         *
         * @param audio_id
         * @return
         */
        AudioListBean loadAudioLocalData(String audio_id);

        /**
         * 保存音频下载几率
         *  @param url
         * @param section_id
         * @param video_type
         * @param listBean
         */
        DownloadAudioBean saveDownloadRecord(String url, int section_id, AudioListBean.DataBean.HeadBean video_type,
                                             String title, String time, AudioListBean.DataBean.ListBean listBean);

        /**
         * 获取音频下载的数据
         *
         * @param video_type
         * @return
         */
        List<DownloadAudioBean> getDownloadRecord(String video_type);

        /**
         * 获取正在下载的数据
         *
         * @return
         */
        List<DownloadAudioBean> loadDownloadData();

    }

    abstract class DetailPresenter extends BasePresenter<DetailView, Model> {

        /**
         * 从网络获取详情数据
         *
         * @param broadcast_id
         */
        public abstract void loadDetailData(String broadcast_id);

        /**
         * 从网络获取音频集合数据
         *
         * @param audio_id
         */
        public abstract void loadAudioListData(String audio_id);

        /**
         * 保存音频下载记录
         *
         * @param url
         * @param section_id
         * @param video_type
         */
        public abstract DownloadAudioBean saveDownloadRecord(String url, int section_id,
                                                             AudioListBean.DataBean.HeadBean video_type,
                                                             String title,String time,AudioListBean.DataBean.ListBean listBean);

        /**
         * 获取音频下载的数据
         *
         * @param video_type
         * @return
         */
        abstract List<DownloadAudioBean> getDownloadRecord(String video_type);


        /**
         * 获取正在下载的数据
         */
        public abstract List<DownloadAudioBean> loadDownloadData();

        public abstract List<DownloadAudioBean> getLocalData(int broadcast_id);
    }

}
