package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import android.content.Context;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：下载管理类
 * @作者：祝明
 * @创建时间：2017/7/12 21:47
 */

public class DownloadManager {

    private static DownloadManager mDownloadManager;

    public static DownloadManager getInstance() {
        if (mDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (mDownloadManager == null) {
                    mDownloadManager = new DownloadManager();
                }
            }
        }
        return mDownloadManager;
    }

    private List<FileDownloadSampleListener> mDownloadListeners = new ArrayList<>(); // 下载进度


    private FileDownloadSampleListener taskDownloadListener = new FileDownloadSampleListener() {

        @Override
        public void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            LogUtils.i("pending");
            for (FileDownloadSampleListener listener : mDownloadListeners) {
                listener.pending(task, soFarBytes, totalBytes);
            }
        }

        @Override
        public void started(BaseDownloadTask task) {
            LogUtils.i("started");
            for (FileDownloadSampleListener listener : mDownloadListeners) {
                listener.started(task);
            }
        }

        @Override
        public void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
            LogUtils.i("connected");
            for (FileDownloadSampleListener listener : mDownloadListeners) {
                listener.connected(task, etag, isContinue, soFarBytes, totalBytes);
            }
        }

        @Override
        public void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            LogUtils.i("progress");
            for (FileDownloadSampleListener listener : mDownloadListeners) {
                listener.progress(task, soFarBytes, totalBytes);
            }
        }

        @Override
        public void error(BaseDownloadTask task, Throwable e) {
            LogUtils.i("error");
            for (FileDownloadSampleListener listener : mDownloadListeners) {
                listener.error(task, e);
            }
        }

        @Override
        public void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            LogUtils.i("paused");
            for (FileDownloadSampleListener listener : mDownloadListeners) {
                listener.paused(task, soFarBytes, totalBytes);
            }
        }

        @Override
        public void completed(BaseDownloadTask task) {
            LogUtils.i("completed");
            // 更新下载记录
            removeTask(task.getId());

            for (FileDownloadSampleListener listener : mDownloadListeners) {
                listener.completed(task);
            }
        }
    };

    private void removeTask(int id) {
        saveDownloadBean(downloadMap.get(id));
    }

    /**
     * 所有下载的集合
     */
    Map<Integer, DownloadAudioBean> downloadMap = new HashMap<>();
    Map<String, Integer> downloadIdMap = new HashMap<>();


    public void addListener(FileDownloadSampleListener listener) {
        if (listener != null) {
            mDownloadListeners.add(listener);
        }
    }

    public void removeListener(FileDownloadSampleListener listener) {
        if (listener != null) {
            mDownloadListeners.remove(listener);
        }
    }

    public void startDownloadTask(DownloadAudioBean downloadAudioBean, Context context) {
        FileDownloader.setup(context);
        String url = downloadAudioBean.getUrl();
        String defaultSaveFilePath = FileDownloadUtils.getDefaultSaveFilePath(url);
        BaseDownloadTask downloadTask = createDownloadTask(url, defaultSaveFilePath);
        downloadMap.put(downloadTask.getId(), downloadAudioBean);
        downloadIdMap.put(url, downloadTask.getId());
    }

    private BaseDownloadTask createDownloadTask(String url, String path) {

        return FileDownloader.getImpl().create(url)
                .setPath(path)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setListener(taskDownloadListener);
    }


    public static void saveDownloadBean(DownloadAudioBean downloadAudioBean) {
        downloadAudioBean.setState("1");
        DBHelper.getInstance().save(DownloadAudioBean.class, downloadAudioBean, "section_id", downloadAudioBean.getSection_id());

    }


}
