package com.soul.androidcompilptions.rxandretrofi.ui.huai.audio;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioBean;
import com.soul.androidcompilptions.rxandretrofi.config.ActionConstant;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.detail.DetailActivity;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.detail.DetailPresenter;
import com.soul.library.base.BaseAdapter;
import com.soul.library.utils.ThreadFactory;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/5 9:30
 */

public class AudioAdapter extends BaseAdapter<AudioBean.DataBean.CirculationBean> {


    private final DetailPresenter mDetailPresenter;

    public AudioAdapter(Context context, List data) {
        super(context, R.layout.item_voice, data);
        mDetailPresenter = new DetailPresenter();
    }

    public void setData(List<AudioBean.DataBean.CirculationBean> data) {
        mDatas = data;
    }

    @Override
    protected void convert(ViewHolder viewHolder, AudioBean.DataBean.CirculationBean circulationBean, int i) {
        viewHolder.setText(R.id.tv_title, circulationBean.getSubject());
        viewHolder.setText(R.id.tv_time, circulationBean.getBegin_time() + "-" + circulationBean.getEnd_time());
        AudioBean.DataBean.CirculationBean.MentorBean mentor = circulationBean.getMentor();
        viewHolder.setText(R.id.name1, mentor.getUser_name());
        ImageView avatar = viewHolder.getView(R.id.iv_avatar1);
        Glide.with(mContext)
                .load(mentor.getAvatar())
                .centerCrop()
                .into(avatar);
        ThreadFactory.getNormaPool().execute(() ->
                mDetailPresenter.loadDetailData(circulationBean.getBroadcast_id() + ""));

        ;
        viewHolder.getConvertView().setOnClickListener(e -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(ActionConstant.EXTRA_BROADCAST_ID, circulationBean.getBroadcast_id());
            mContext.startActivity(intent);
        });
    }
}
