package com.hongfans.library.download;

import android.content.Context;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:RearviewVirtual
 * @创建时间：2018/3/6 11:35
 */

public class DownloadManager implements IDownloadManager {


    /**
     * integer 下载任务id
     * downloadProgressListener 监听
     */
    HashMap<Integer, DownloadProgressListener> mListenerList = new HashMap<>();

    private static IDownloadManager sDownloadManager;

    public static IDownloadManager getInstance() {

        if (sDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (sDownloadManager == null) {
                    sDownloadManager = new DownloadManager();
                }
            }
        }
        return sDownloadManager;
    }


    @Override
    public int addDownload(Context context, String url, String path) throws Exception {
        FileDownloader.setup(context);
        int taskId = FileDownloadUtils.generateId(url, path);
        BaseDownloadTask task = FileDownloader.getImpl().create(url)
                .setPath(path)
                .setAutoRetryTimes(3)
                .setCallbackProgressTimes(300)
                .setSyncCallback(true)
                .setMinIntervalUpdateSpeed(300)
                .setListener(mAdTaskDownloadListener);
        task.start();
        return taskId;
    }

    @Override
    public void deleteDownload(int taskId, String path) {
        FileDownloader.getImpl().clear(taskId, path);
        mListenerList.remove(taskId);
    }

    @Override
    public void setDownloadListener(int taskId, DownloadProgressListener downloadProgressListener) {
        mListenerList.put(taskId, downloadProgressListener);
    }

    @Override
    public void removeDownloadListener(int taskId) {
        mListenerList.remove(taskId);
    }

    @Override
    public String getDownloadInfo(int taskId) {
        return null;
    }

    @Override
    public List<String> getAllDownloadInfo() {
        return null;
    }

    @Override
    public List<String> getAllDownloadedInfo() {
        return null;
    }

    @Override
    public void pauseDownload(int taskId) {
        FileDownloader.getImpl().pause(taskId);
    }

    @Override
    public void pauseAllDownload() {

    }

    @Override
    public void resumeDownload(int taskId) {

    }

    @Override
    public void resumeAllDownload() {

    }

    @Override
    public void onDestroy() {
        FileDownloader.getImpl().unBindService();
    }


    /**
     * 下载监听
     */
    private FileDownloadSampleListener mAdTaskDownloadListener = new FileDownloadSampleListener() {

        @Override
        public void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.pending(task, soFarBytes, totalBytes);
            Log.i("Tag", "pending");
            for (Map.Entry<Integer, DownloadProgressListener> entry : mListenerList.entrySet()) {
                if (task.getId() == entry.getKey()) {
                    DownloadProgressListener downloadProgressListener = entry.getValue();
                    downloadProgressListener.pending();
                }
            }
        }

        @Override
        public void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.progress(task, soFarBytes, totalBytes);
            Log.i("Tag", "progress");
            for (Map.Entry<Integer, DownloadProgressListener> entry : mListenerList.entrySet()) {
                if (task.getId() == entry.getKey()) {
                    DownloadProgressListener downloadProgressListener = entry.getValue();
                    downloadProgressListener.progress(soFarBytes, totalBytes);
                }
            }
        }

        @Override
        public void completed(BaseDownloadTask task) {
            Log.i("Tag", "completed");
            //完成下载
            for (Map.Entry<Integer, DownloadProgressListener> entry : mListenerList.entrySet()) {
                if (task.getId() == entry.getKey()) {
                    Log.i("Tag", "completed,getId");
                    DownloadProgressListener downloadProgressListener = entry.getValue();
                    downloadProgressListener.completed();
                }
            }
        }

        @Override
        public void error(BaseDownloadTask task, Throwable e) {
            super.error(task, e);
            Log.i("Tag", "error");
            for (Map.Entry<Integer, DownloadProgressListener> entry : mListenerList.entrySet()) {
                if (task.getId() == entry.getKey()) {
                    DownloadProgressListener downloadProgressListener = entry.getValue();
                    downloadProgressListener.error(e);
                }
            }
        }
    };
}
