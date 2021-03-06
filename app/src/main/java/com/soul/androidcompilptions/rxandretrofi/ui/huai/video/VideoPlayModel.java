package com.soul.androidcompilptions.rxandretrofi.ui.huai.video;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.api.DrakeetFactory;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoUrlBean;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.Constants;

import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 23:01
 */

public class VideoPlayModel implements VideoPlayContract.Model {

    @Override
    public Observable<VideoBean> loadVideoData(int course_id, int spec_id) {
        return DrakeetFactory.getBadBoyResource().getVideoData(
                course_id + "",
                spec_id + "",
                "video",
                "0",

                Constants.PHONE_SN,
                Constants.SESSION_ID,
                Constants.LOGIN_USER_ID,
                Constants.VERSION,
                Constants.APP_CHANNEL,
                Constants.PCID
        );
    }

    @Override
    public Observable<VideoUrlBean> loadVideoUrlData(int course_id, int spec_id, int section_id) {
        return DrakeetFactory.getBadBoyResource().getVideoUrlData(
                course_id + "",
                spec_id + "",
                section_id + "",
                "video",
                "0",

                Constants.PHONE_SN,
                Constants.SESSION_ID,
                Constants.LOGIN_USER_ID,
                Constants.VERSION,
                Constants.APP_CHANNEL,
                Constants.PCID
        );
    }


    @Override
    public Observable<AudioListBean> loadVideoListData(int course_id, int spec_id, int teach_id) {

        return DrakeetFactory.getBadBoyResource().getVideoListData(
                course_id + "",
                spec_id + "",
                "video",
                teach_id + "",

                Constants.PHONE_SN,
                Constants.SESSION_ID,
                Constants.LOGIN_USER_ID,
                Constants.VERSION,
                Constants.APP_CHANNEL,
                Constants.PCID
        );
    }

    /**
     * 视频
     *
     * @param videoBean
     * @param course_id
     */
    @Override
    public void saveVideoData(VideoBean videoBean, int course_id) {
        Gson gson = new Gson();
        String s = gson.toJson(videoBean);
        DBHelper.getInstance().insertOrUpdateValue(VideoBean.class.getName() + (course_id + ""), s);
    }

    @Override
    public void saveVideoData(VideoBean videoBean, String course_name) {

    }

    /**
     * 视频地址
     *
     * @param videoBean
     * @param section_id
     */
    @Override
    public void saveVideoUrl(VideoUrlBean videoBean, int section_id) {
        Gson gson = new Gson();
        String s = gson.toJson(videoBean);
        DBHelper.getInstance().insertOrUpdateValue(VideoUrlBean.class.getName() + (section_id + ""), s);
    }


    /**
     * 视频集合
     *
     * @param audioListBean
     */
    @Override
    public void saveAudioData(AudioListBean audioListBean, int teach_id) {
        Gson gson = new Gson();
        String s = gson.toJson(audioListBean);
        DBHelper.getInstance().insertOrUpdateValue(AudioListBean.class.getName() + (teach_id + ""), s);
    }

    @Override
    public VideoBean loadVideoLocalData(int course_id, int spec_id) {
        Gson gson = new Gson();
        String jsonString = DBHelper.getInstance().queryValue(VideoBean.class.getName() + (course_id + ""));
        VideoBean o = gson.fromJson(jsonString, new TypeToken<VideoBean>() {
        }.getType());
        return o;
    }

    @Override
    public VideoBean loadVideoLocalData(String courseName) {
        return null;
    }

    @Override
    public VideoUrlBean loadVideoUrlLocalData(int course_id, int spec_id, int section_id) {
        Gson gson = new Gson();
        String jsonString = DBHelper.getInstance().queryValue(VideoUrlBean.class.getName() + (section_id + ""));
        VideoUrlBean o = gson.fromJson(jsonString, new TypeToken<VideoUrlBean>() {
        }.getType());
        return o;
    }

    @Override
    public AudioListBean loadAudioListData(int course_id, int spec_id, int teach_id) {
        Gson gson = new Gson();
        String jsonString = DBHelper.getInstance().queryValue(AudioListBean.class.getName() + (teach_id + ""));
        AudioListBean o = gson.fromJson(jsonString, new TypeToken<AudioListBean>() {
        }.getType());
        return o;
    }


}
