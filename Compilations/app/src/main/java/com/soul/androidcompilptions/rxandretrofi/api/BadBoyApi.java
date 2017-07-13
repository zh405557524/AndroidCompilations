package com.soul.androidcompilptions.rxandretrofi.api;

import com.soul.androidcompilptions.rxandretrofi.bean.AudioBean;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DetailBean;
import com.soul.androidcompilptions.rxandretrofi.bean.MyCourseBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoBean;
import com.soul.androidcompilptions.rxandretrofi.bean.VideoUrlBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/4 18:51
 */

public interface BadBoyApi {


    /**
     * 获取音频数据
     *
     * @return
     */

    @GET("teachingArea/list")
    Observable<AudioBean> getAudioData(
            @Query("schedule_date") String schedule_date,
            @Query("phone_sn") String phone_sn,
            @Query("session_id") String session_id,
            @Query("login_user_id") String login_user_id,
            @Query("version") String version,
            @Query("app_channel") String app_channel,
            @Query("pcid") String pcid
    );

    /**
     * 获取音频数据
     *
     * @return
     */

    @GET("homework/homework_detail")
    Observable<DetailBean> getDetailData(
            @Query("chatgroup_id") String chatgroup_id,
            @Query("broadcast_id") String broadcast_id,
            @Query("homework_id") String homework_id,
            @Query("line") String line,

            @Query("phone_sn") String phone_sn,
            @Query("session_id") String session_id,
            @Query("login_user_id") String login_user_id,
            @Query("version") String version,
            @Query("app_channel") String app_channel,
            @Query("pcid") String pcid
    );

    @GET("audio_buffer/list_audio")
    Observable<AudioListBean> getAudioListData(
            @Query("audio_id") String audio_id,
            @Query("audio_type") String audio_type,
            @Query("chatgroup_id") String chatgroup_id,
            @Query("page_no") String page_no,

            @Query("phone_sn") String phone_sn,
            @Query("session_id") String session_id,
            @Query("login_user_id") String login_user_id,
            @Query("version") String version,
            @Query("app_channel") String app_channel,
            @Query("pcid") String pcid

    );

    @GET("course/list_user_course")
    Observable<MyCourseBean> getMyCourseData(
            @Query("phone_sn") String phone_sn,
            @Query("session_id") String session_id,
            @Query("login_user_id") String login_user_id,
            @Query("version") String version,
            @Query("app_channel") String app_channel,
            @Query("pcid") String pcid

    );

    @GET("course/section")
    Observable<VideoBean> getVideoData(
            @Query("course_id") String course_id,
            @Query("spec_id") String spec_id,
            @Query("teach_type") String teach_type,
            @Query("teach_id") String teach_id,

            @Query("phone_sn") String phone_sn,
            @Query("session_id") String session_id,
            @Query("login_user_id") String login_user_id,
            @Query("version") String version,
            @Query("app_channel") String app_channel,
            @Query("pcid") String pcid

    );

    @GET("course/section_url")
    Observable<VideoUrlBean> getVideoUrlData(
            @Query("course_id") String course_id,
            @Query("spec_id") String spec_id,
            @Query("section_id") String teach_id,
            @Query("teach_type") String teach_type,
            @Query("line") String line,

            @Query("phone_sn") String phone_sn,
            @Query("session_id") String session_id,
            @Query("login_user_id") String login_user_id,
            @Query("version") String version,
            @Query("app_channel") String app_channel,
            @Query("pcid") String pcid

    );

    @GET("course/list_section_buffer")
    Observable<AudioListBean> getVideoListData(
            @Query("course_id") String course_id,
            @Query("spec_id") String spec_id,
            @Query("teach_type") String teach_type,
            @Query("teach_id") String teach_id,

            @Query("phone_sn") String phone_sn,
            @Query("session_id") String session_id,
            @Query("login_user_id") String login_user_id,
            @Query("version") String version,
            @Query("app_channel") String app_channel,
            @Query("pcid") String pcid

    );

}
