package com.soul.frame.download;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络下载框架
 */
public class DownloadManager implements IDownloadManager {

    private String path = Global.getInnerDataDir();

    /**
     * 任务监听集合
     * int 任务id
     * downloadProgressListener 任务进度监听
     */
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, DownloadProgressListener> mListenerList;

    /**
     * 任务集合
     */
    private SparseArray<BaseDownloadTask> mTasks;

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

    @SuppressLint("UseSparseArrays")
    private DownloadManager() {
        mTasks = new SparseArray<>();
        mListenerList = new HashMap<>();
    }

    /**
     * 设置下载路径
     *
     * @param path
     */
    @Override
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 添加下载 (小于2g数据)
     *
     * @param context
     * @param url      资源地址
     * @param fileName 文件名称
     * @return
     * @throws Exception
     */
    @Override
    public int addDownload(Context context, String url, String fileName) throws Exception {
        FileDownloader.setup(context);
        String tempPath = path + fileName;
        int taskId = FileDownloadUtils.generateId(url, tempPath);
        BaseDownloadTask task = FileDownloader.getImpl().create(url)
                .setPath(tempPath)
                .setAutoRetryTimes(3)
                .setCallbackProgressTimes(100)
                .setSyncCallback(true)
                .setMinIntervalUpdateSpeed(100)
                .setListener(mAdTaskDownloadListener);
        task.start();
        mTasks.put(taskId, task);
        return taskId;
    }

    /**
     * 添加下载监听
     *
     * @param taskId                   任务id
     * @param downloadProgressListener 监听
     */
    @Override
    public void addDownloadListener(int taskId, DownloadProgressListener downloadProgressListener) {
        mListenerList.put(taskId, downloadProgressListener);
    }

    @Override
    public void removeDownloadListener(int taskId) {
        mListenerList.remove(taskId);
    }

    @Override
    public String getDownloadInfo(int taskId) {
        BaseDownloadTask baseDownloadTask = mTasks.get(taskId);
        if (baseDownloadTask != null) {
            return baseDownloadTask.getFilename();
        }
        return "";
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
        BaseDownloadTask baseDownloadTask = mTasks.get(taskId);
        if (baseDownloadTask != null) {
            baseDownloadTask.pause();
        }
    }

    @Override
    public void pauseAllDownload() {
        for (int i = 0; i < mTasks.size(); i++) {
            BaseDownloadTask baseDownloadTask = mTasks.valueAt(i);
            if (baseDownloadTask != null) {
                baseDownloadTask.pause();
            }
        }
    }

    @Override
    public void resumeDownload(int taskId) {
        BaseDownloadTask baseDownloadTask = mTasks.get(taskId);
        if (baseDownloadTask != null) {
            baseDownloadTask.start();
        }
    }

    @Override
    public void resumeAllDownload() {
        for (int i = 0; i < mTasks.size(); i++) {
            BaseDownloadTask baseDownloadTask = mTasks.valueAt(i);
            if (baseDownloadTask != null) {
                baseDownloadTask.start();
            }
        }
    }


    @Override
    public void deleteDownload(int taskId) {
        BaseDownloadTask baseDownloadTask = mTasks.get(taskId);
        if (baseDownloadTask != null) {
            FileDownloader.getImpl().clear(taskId, baseDownloadTask.getPath());
            mListenerList.remove(taskId);
        }
    }

    @Override
    public void deleteAllDownload() {
        for (int i = 0; i < mTasks.size(); i++) {
            BaseDownloadTask baseDownloadTask = mTasks.valueAt(i);
            if (baseDownloadTask != null) {
                FileDownloader.getImpl().clear(baseDownloadTask.getId(), baseDownloadTask.getPath());
                mListenerList.remove(baseDownloadTask.getId());
            }
        }
    }


    @Override
    public void onDestroy() {
        pauseAllDownload();
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
                    if (downloadProgressListener != null) {
                        downloadProgressListener.pending(task.getId());
                    }
                    break;
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
                    if (downloadProgressListener != null) {
                        downloadProgressListener.progress(task.getId(), totalBytes, soFarBytes);
                    }
                    break;
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
                    if (downloadProgressListener != null) {
                        downloadProgressListener.completed(task.getId());
                    }
                    break;
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
                    if (downloadProgressListener != null) {
                        downloadProgressListener.error(task.getId(), e);
                    }
                    break;
                }
            }
        }
    };
}
