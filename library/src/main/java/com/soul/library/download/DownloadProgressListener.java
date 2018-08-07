package com.hongfans.library.download;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:sdk
 * @创建时间：2017/12/20 17:39
 */

public interface DownloadProgressListener {


    /**
     * 连接资源中
     */
    void pending();

    /**
     * 下载进度
     *
     * @param soFarBytes 总进度
     * @param totalBytes 下载进度
     */
    void progress(int soFarBytes, int totalBytes);

    /**
     * 下载完成
     */
    void completed();

    /**
     * 下载错误
     *
     * @param e
     */
    void error(Throwable e);

}
