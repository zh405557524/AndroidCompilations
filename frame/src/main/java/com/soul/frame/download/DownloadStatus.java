package com.soul.frame.download;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:sdk
 * @创建时间：2017/12/29 15:10
 */

public class DownloadStatus {


    /**
     * 下载出错
     */
    public static final byte ERROR = -1;

    /**
     * 下载中
     */
    public static final byte DOWNLOADING = 0;
    /**
     * 下载完成
     */
    public static final byte DOWNLOADED = 1;
    /**
     * 下载暂停
     */
    public static final byte DOWNLOAD_PAUSE = 2;


}
