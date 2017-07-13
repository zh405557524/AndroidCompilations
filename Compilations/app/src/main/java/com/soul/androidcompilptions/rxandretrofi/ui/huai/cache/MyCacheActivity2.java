package com.soul.androidcompilptions.rxandretrofi.ui.huai.cache;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.androidcompilptions.rxandretrofi.config.ActionConstant;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.detail.DetailActivity;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.detail.DownloadActivity;
import com.soul.androidcompilptions.rxandretrofi.utils.ImageUtil;
import com.soul.library.base.BaseAdapter;
import com.soul.library.base.BaseRxActivity;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCacheActivity2 extends BaseRxActivity<MyCachePresenter> implements
        MyCacheContract.MyCacheView {


    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_cache) TextView tvCache;
    @BindView(R.id.rv_recyclerView) RecyclerView rvRecyclerView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_cache;
    }

    @Override
    protected MyCachePresenter loadPresenter() {
        return new MyCachePresenter();
    }

    @Override
    protected void initView() {
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        mPresenter.loadCacheData();
    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.iv_back, R.id.tv_cache})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_cache:
                Intent intent = new Intent(mContext, DownloadActivity.class);
                startActivity(intent);
                break;
        }

    }


    @Override
    public void loadDownCacheSucceed(List<List<DownloadAudioBean>> audioListBean) {

        if (audioListBean != null && audioListBean.size() > 0) {
            int position = getIntent().getIntExtra("position", 0);
            List<DownloadAudioBean> downloadAudioBeans = audioListBean.get(position);
            tvTitle.setText(downloadAudioBeans.get(0).getVideo_name());
            rvRecyclerView.setAdapter(new BaseAdapter<DownloadAudioBean>(mContext, R.layout.item_my_course, downloadAudioBeans) {
                @Override
                protected void convert(ViewHolder viewHolder, DownloadAudioBean list, int i) {
                    if (list == null) {
                        return;
                    }
                    ImageView view = viewHolder.getView(R.id.iv_photo);
                    ImageUtil.setImageView(view, list.getPhoto());
                    viewHolder.setText(R.id.tv_title, list.getTitle());
                    View convertView = viewHolder.getConvertView();
                    convertView.setOnClickListener(view1 -> {
                        if (list.getVideo_type().equals("broadcast_history")) {
                            //音频
                            Intent intent = new Intent(MyCacheActivity2.this, DetailActivity.class);
                            intent.putExtra(ActionConstant.EXTRA_BROADCAST_ID, Integer.valueOf(list.getSection_id()));
                            startActivity(intent);
                        } else {
                            //视频
                            Intent intent = new Intent(MyCacheActivity2.this, DetailActivity.class);
                            intent.putExtra(ActionConstant.EXTRA_BROADCAST_ID, Integer.valueOf(list.getSection_id()));
                            startActivity(intent);
                        }
                    });

                }
            });
        }

    }
}
