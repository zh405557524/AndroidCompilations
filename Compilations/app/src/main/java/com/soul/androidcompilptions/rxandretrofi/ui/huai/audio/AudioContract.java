package com.soul.androidcompilptions.rxandretrofi.ui.huai.audio;

import com.soul.androidcompilptions.rxandretrofi.bean.AudioBean;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IView;

import rx.Observable;


/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/4 18:55
 */

public interface AudioContract {

    interface VoiceView extends IView {
        /**
         * 获取音频数据成功
         *
         * @param audioBean
         */
        void loadVoiceDataSucceed(AudioBean audioBean);

        /**
         * 获取音频数据失败
         *
         * @param error
         */
        void loadVoiceDataFailure(String error);

    }

    interface Model extends IModel {

        /**
         * @param date 日期
         * @return
         */
        Observable<AudioBean> loadVoiceData(String date);

        void saveVoiceLocalData(AudioBean audioBean, String date);

        AudioBean loadLocalData(String s);
    }

    abstract class VoicePresenter extends BasePresenter<VoiceView, Model> {

        /**
         * @param date 日期
         */
        public abstract void loadVoiceData(String date);

        public abstract AudioBean loadLocalData(String s);
    }


}
