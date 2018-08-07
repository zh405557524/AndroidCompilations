package com.hongfans.library.download;

import android.content.Context;

import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:RearviewVirtual
 * @创建时间：2018/3/6 11:36
 */

public interface IDownloadManager {


    /**
     * 添加下载
     *
     * @param url  资源地址
     * @param path 文件下载路径
     */
    int addDownload(Context context, String url, String path) throws Exception;


    /**
     * 删除下载(包括文件)
     *
     * @param taskId
     */
    void deleteDownload(int taskId, String path);

    /**
     * 设置下载监听
     *
     * @param taskId
     * @param downloadProgressListener 监听
     */
    void setDownloadListener(int taskId, DownloadProgressListener downloadProgressListener);


    void removeDownloadListener(int taskId);

    /**
     * 获取下载状态
     *
     * @param taskId
     * @return
     */
    String getDownloadInfo(int taskId);


    /**
     * 获取所有正在下载的数据
     *
     * @return
     */
    List<String> getAllDownloadInfo();

    /**
     * 获取已下载的数据
     *
     * @return
     */
    List<String> getAllDownloadedInfo();

    /**
     * 暂停下载
     *
     * @param taskId
     */
    void pauseDownload(int taskId);

    /**
     * 暂停所有下载
     */
    void pauseAllDownload();

    /**
     * 恢复下载
     *
     * @param taskId
     */
    void resumeDownload(int taskId);


    /**
     * 恢复所有下载
     */
    void resumeAllDownload();

    /**
     * app退出时，暂停下载
     */
    void onDestroy();

}
