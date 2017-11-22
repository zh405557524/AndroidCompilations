package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.androidcompilptions.rxandretrofi.utils.FileUtils;
import com.soul.androidcompilptions.rxandretrofi.utils.ImageUtil;
import com.soul.library.base.BaseAdapter;
import com.soul.library.base.BaseRxActivity;
import com.soul.library.utils.LogUtils;
import com.soul.library.utils.ThreadFactory;
import com.soul.library.utils.UIUtils;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 下载缓存
 */
public class DownloadActivity extends BaseRxActivity<DetailContract.DetailPresenter> {


    /**
     * 返回键
     */
    @BindView(R.id.iv_back) ImageView ivBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title) TextView tvTitle;
    /**
     * 全部开始
     */
    @BindView(R.id.tv_allStart) TextView tvAllStart;
    /**
     * 下载的内容
     */
    @BindView(R.id.rv_downFile) RecyclerView rvDownFile;
    private BaseAdapter mBaseAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_down_chach;
    }

    @Override
    protected DetailContract.DetailPresenter loadPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void initView() {
        FileDownloader.setup(mContext);
    }

    @Override
    protected void initData() {
        rvDownFile.setLayoutManager(new LinearLayoutManager(mContext));
        loadData();
    }

    private void loadData() {
        ThreadFactory.getNormaPool().execute(() -> {
            List<DownloadAudioBean> downloadAudioBeans = mPresenter.loadDownloadData();
            UIUtils.postTaskSafely(() -> setAdapter(downloadAudioBeans));
        });
    }

    private Map<Integer, Integer> downloadMap = new HashMap<>();

    private void setAdapter(List<DownloadAudioBean> downloadAudioBeans) {

        //正在下载
        //下载完成
        //暂停下载
        //暂停下载
        if (mBaseAdapter == null) {
            mBaseAdapter = new BaseAdapter<DownloadAudioBean>(mContext, R.layout.itme_down_load, downloadAudioBeans) {

                @Override
                protected void convert(ViewHolder viewHolder, DownloadAudioBean downloadAudioBean, int i) {
                    if (downloadAudioBean != null) {
                        ImageView view = viewHolder.getView(R.id.iv_photo);
                        ImageView ivState = viewHolder.getView(R.id.iv_state);

                        ImageUtil.setCircleImageView(view, downloadAudioBean.getPhoto());
                        viewHolder.setText(R.id.tv_title, downloadAudioBean.getTitle());
                        String video_name = downloadAudioBean.getVideo_name();
                        switch (downloadAudioBean.getState()) {
                            case "0"://正在下载
                                viewHolder.setText(R.id.tv_stateAndType, "正在下载 " + video_name);
                                ivState.setImageResource(R.drawable.play_selector);
                                break;
                            case "1"://下载完成
                                viewHolder.setText(R.id.tv_stateAndType, "下载完成 " + video_name);
                                break;
                            case "2"://暂停下载
                                viewHolder.setText(R.id.tv_stateAndType, "暂停 " + video_name);
                                ivState.setImageResource(R.drawable.pause_selector);
                                break;
                        }
                        ProgressBar progressBar = viewHolder.getView(R.id.pb);
                        TextView state = viewHolder.getView(R.id.tv_state);

                        state.setOnClickListener(view1 -> {

                            if (!"正在下载".equals(state.getText().toString().trim())) {
                                String defaultSaveFilePath = "";
                                if (downloadAudioBean.getUrl().endsWith(".mp3")) {
                                    defaultSaveFilePath = FileUtils.getDir("mp3") + downloadAudioBean.getTitle() + ".mp3";
                                } else {
                                    defaultSaveFilePath = FileUtils.getDir("mp4") + downloadAudioBean.getTitle() + ".mp4";
                                }
                                LogUtils.i("defaultSaveFilePath:" + defaultSaveFilePath);
                                int start = createDownloadTask(downloadAudioBean.getUrl(),
                                        defaultSaveFilePath,
                                        progressBar, downloadAudioBean).start();
                                downloadMap.put(i, start);

                                state.setText("正在下载");
                            } else {
                                //暂停下载
                                state.setText("暂停下载");
                                FileDownloader.getImpl().pause(downloadMap.get(i));
                            }
                        });
                    }
                }
            };
            rvDownFile.setAdapter(mBaseAdapter);
        } else {
            mBaseAdapter.getDatas().clear();
            mBaseAdapter.getDatas().addAll(downloadAudioBeans);
            mBaseAdapter.notifyDataChanged();
        }
    }


    private BaseDownloadTask createDownloadTask(String url, String path, ProgressBar progressBar, DownloadAudioBean downloadAudioBean) {
        downloadAudioBean.setLocalUrl(path);
        return FileDownloader.getImpl().create(url)
                .setPath(path)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setListener(new FileDownloadSampleListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.pending(task, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);

                        if (totalBytes == -1) {
                            // chunked transfer encoding data
                            progressBar.setIndeterminate(true);
                        } else {
                            progressBar.setMax(totalBytes);
                            progressBar.setProgress(soFarBytes);
                        }
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        super.connected(task, etag, isContinue, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.paused(task, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        LogUtils.i("completed:" + downloadAudioBean.toString());
                        downloadAudioBean.setState("1");
                        DBHelper.getInstance().save(DownloadAudioBean.class, downloadAudioBean, "section_id", downloadAudioBean.getSection_id());
                        loadData();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        super.warn(task);
                    }
                });
    }


    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.iv_back, R.id.tv_allStart})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_allStart:
                break;
        }
    }

}
