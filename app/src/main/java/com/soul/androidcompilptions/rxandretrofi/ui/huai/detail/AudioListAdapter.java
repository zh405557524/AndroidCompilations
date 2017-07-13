package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import android.content.Context;
import android.view.View;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.library.base.BaseAdapter;
import com.soul.library.utils.FileHelp;
import com.soul.library.utils.LogUtils;
import com.soul.library.utils.ThreadFactory;
import com.soul.library.utils.UIUtils;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/9 16:51
 */

public class AudioListAdapter extends BaseAdapter<AudioListBean.DataBean.ListBean> {

    /**
     * 需要显示的position
     */
    private List<Integer> showList = new ArrayList<>();

    /**
     * 选中的position
     */
    private List<Integer> selectList = new ArrayList<>();

    private DetailPresenter mDetailPresenter;
    private AudioListBean.DataBean.HeadBean mHeadBean;

    public AudioListAdapter(Context context, List<AudioListBean.DataBean.ListBean> data, DetailPresenter detailPresenter, AudioListBean.DataBean.HeadBean video_type) {
        super(context, R.layout.item_audio_down, data);
        mDetailPresenter = detailPresenter;
        mHeadBean = video_type;
        ThreadFactory.getNormaPool().execute(() -> {
            List<DownloadAudioBean> downloadRecord = mDetailPresenter.getDownloadRecord(mHeadBean.getVideo_type());
            List<String> urls = new ArrayList<>();
            for (DownloadAudioBean downloadAudioBean : downloadRecord) {
                urls.add(downloadAudioBean.getSection_id() + "");
            }

            for (int i = 0; i < data.size(); i++) {
                if (urls.contains(data.get(i).getSection_id() + "")) {
                    showList.add(i);
                    DownloadAudioBean audioBean = getAudioBean(data.get(i).getSection_id() + "", downloadRecord);
                    if ("1".equals(audioBean.getState())) {
                        selectList.add(i);
                    }
                }

            }
            UIUtils.postTaskSafely(() -> notifyDataChanged());

        });
    }

    public DownloadAudioBean getAudioBean(String url, List<DownloadAudioBean> list) {

        for (DownloadAudioBean downloadAudioBean : list) {
            if (url.equals(downloadAudioBean.getSection_id())) {
                return downloadAudioBean;
            }
        }
        return null;

    }

    @Override
    protected void convert(ViewHolder viewHolder, AudioListBean.DataBean.ListBean listBean, int i) {
        if (listBean != null) {
            viewHolder.setText(R.id.tv_item_title, listBean.getSection_name());
            viewHolder.setText(R.id.tv_nameAndTime, listBean.getMentor_user_name() + "  " + listBean.getCreate_time());
            viewHolder.setText(R.id.tv_time_size, FileHelp.formatSize(listBean.getM3u8_size()));
            View rlRoot = viewHolder.getView(R.id.rl_root);
            View downStateView = viewHolder.getView(R.id.iv_downState);
            if (showList.contains(i)) {
                downStateView.setVisibility(View.VISIBLE);
            } else {
                downStateView.setVisibility(View.GONE);
            }

            if (selectList.contains(i)) {
                downStateView.setSelected(true);
            } else {
                downStateView.setSelected(false);
            }

            rlRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //发送下载通知
                    if (downStateView.isSelected()){
                        return;
                    }
                    downStateView.setVisibility(View.VISIBLE);
                    showList.add(i);
                    DownloadAudioBean downloadAudioBean = mDetailPresenter.saveDownloadRecord(
                            listBean.getUrl(), listBean.getSection_id(), mHeadBean, listBean.getSection_name(),listBean.getCreate_time(),listBean);
//                    ThreadFactory.getNormaPool().execute(() -> DownloadManager.getInstance().startDownloadTask(downloadAudioBean, mContext));
                    LogUtils.i("保存:" + listBean.getSection_id());
                }
            });
        }
    }
}
