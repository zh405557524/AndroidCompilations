package com.soul.frame.download;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:sdk
 * @创建时间：2017/12/20 17:39
 */

public interface DownloadProgressListener {


    /**
     * 连接资源中
     *
     * @param taskId 任务id
     */
    void pending(int taskId);

    /**
     * 下载进度
     *
     * @param total    总进度
     * @param progress 下载进度
     */
    void progress(int taskId, int total, int progress);

    /**
     * 下载完成
     *
     * @param taskId 任务id
     */
    void completed(int taskId);

    /**
     * 下载错误
     *
     * @param e      异常错误
     * @param taskId 任务id
     */
    void error(int taskId, Throwable e);

}
