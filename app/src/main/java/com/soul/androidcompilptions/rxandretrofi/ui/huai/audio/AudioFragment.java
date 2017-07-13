package com.soul.androidcompilptions.rxandretrofi.ui.huai.audio;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioBean;
import com.soul.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 音频
 */
public class AudioFragment extends BaseFragment<AudioPresenter> implements AudioContract.VoiceView {

    @BindView(R.id.rv_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_date)
    TextView tvDate;

    List<AudioBean.DataBean.CirculationBean> mData = new ArrayList<>();
    private AudioAdapter mVoiceAdapter;

    @Override
    protected AudioPresenter loadPresenter() {

        return new AudioPresenter();
    }

    @Override
    protected View initView(ViewGroup container) {
        return View.inflate(mActivity, R.layout.fragment_voice, null);
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String date = arguments.getString("date");
        tvDate.setText(date);
        date = "&" + date;
        date = date.replace("&20", "");
        date = date.replace("-", "");
        Log.i("Tag", "date:" + date);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        AudioBean audioBean = mPresenter.loadLocalData(date);
        if (audioBean != null && audioBean.getData() != null) {
            mVoiceAdapter = new AudioAdapter(mActivity, audioBean.getData().getCirculation());
            mRecyclerView.setAdapter(mVoiceAdapter);
        }
        mPresenter.loadVoiceData(date);
    }

    @Override
    public void loadVoiceDataSucceed(AudioBean audioBean) {
        if (audioBean != null) {
            if (mVoiceAdapter == null) {
                mVoiceAdapter = new AudioAdapter(mActivity, audioBean.getData().getCirculation());
                mRecyclerView.setAdapter(mVoiceAdapter);
            } else {
                mVoiceAdapter.setData(audioBean.getData().getCirculation());
                mVoiceAdapter.notifyDataChanged();
            }
        }
    }


    @Override
    public void loadVoiceDataFailure(String error) {

    }
}
