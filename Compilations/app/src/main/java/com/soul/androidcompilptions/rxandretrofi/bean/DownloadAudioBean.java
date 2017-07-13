package com.soul.androidcompilptions.rxandretrofi.bean;

import android.support.annotation.NonNull;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/9 21:52
 */

public class DownloadAudioBean extends BaseBean implements Comparable<DownloadAudioBean> {

    public DownloadAudioBean(String url, String section_id) {
        this.url = url;
        this.section_id = section_id;
    }

    public DownloadAudioBean(String url, String section_id, String video_type, String video_name) {
        this.url = url;
        this.section_id = section_id;
        this.video_type = video_type;
        this.video_name = video_name;
        this.state = "0";//默认正在下载
    }

    public DownloadAudioBean(String url, String section_id, String video_type, String video_name, String photo) {
        this.url = url;
        this.section_id = section_id;
        this.video_type = video_type;
        this.video_name = video_name;
        this.photo = photo;
    }

    public DownloadAudioBean(String url, String section_id, String video_type, String video_name, String photo, String title) {
        this.url = url;
        this.section_id = section_id;
        this.video_type = video_type;
        this.video_name = video_name;
        this.photo = photo;
        this.title = title;
    }

    private String url;//下载地址
    private String section_id;//id
    private String max;//总大小
    private String progress;//当前下载的大小
    private String video_type;//  音频的类型. 1 broadcast_history 2 "搭讪技巧"
    private String video_name;//音频名称
    private String state;//下载状态 "0" 正在下载  "1" 已下载完成 "2"暂停下载
    private String photo;//图片
    private String title;//标题
    private String localUrl;//本地地址
    private String mentor_user_name;

    private String create_time;//创建时间


    public String getMentor_user_name() {
        return mentor_user_name;
    }

    public void setMentor_user_name(String mentor_user_name) {
        this.mentor_user_name = mentor_user_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public int compareTo(@NonNull DownloadAudioBean o) {
        if (video_name.compareTo(o.getVideo_name()) == 0) {
            return title.compareTo(o.getTitle());
        } else {

            return video_name.compareTo(o.getVideo_name());
        }
    }
}
