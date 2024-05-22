package com.soul.frame.download;

import android.content.Context;

import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:RearviewVirtual
 * @创建时间：2018/3/6 11:36
 */

public interface IDownloadManager {


    void setPath(String path);

    /**
     * 添加下载
     *
     * @param url      资源地址
     * @param fileName 文件名称
     */
    int addDownload(Context context, String url, String fileName) throws Exception;


    /**
     * 设置下载监听
     *
     * @param taskId                   任务id
     * @param downloadProgressListener 监听
     */
    void addDownloadListener(int taskId, DownloadProgressListener downloadProgressListener);


    /**
     * 暂停下载
     *
     * @param taskId 任务id
     */
    void pauseDownload(int taskId);

    /**
     * 暂停所有下载
     */
    void pauseAllDownload();

    /**
     * 恢复下载
     *
     * @param taskId 任务id
     */
    void resumeDownload(int taskId);


    /**
     * 恢复所有下载
     */
    void resumeAllDownload();

    /**
     * 获取下载状态
     *
     * @param taskId 任务id
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
     * 移除下载监听
     *
     * @param taskId 任务id
     */
    void removeDownloadListener(int taskId);


    /**
     * 删除下载(包括文件)
     *
     * @param taskId 任务id
     */
    void deleteDownload(int taskId);

    /**
     * 删除所有任务(包括文件)
     */
    void deleteAllDownload();


    /**
     * app退出时，暂停下载
     */
    void onDestroy();

}
