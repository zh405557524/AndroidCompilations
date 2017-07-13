package com.soul.androidcompilptions.rxandretrofi.ui.huai.mycourse;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.MyCourseBean;
import com.soul.androidcompilptions.rxandretrofi.config.ActionConstant;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.video.VideoPlayActivity;
import com.soul.androidcompilptions.rxandretrofi.utils.ImageUtil;
import com.soul.library.base.BaseAdapter;
import com.soul.library.base.BaseRxActivity;
import com.soul.library.utils.LogUtils;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCourseActivity extends BaseRxActivity<MyCoursePresenter> implements MyCourseContract.MyCourseView {

    @BindView(R.id.rv_myCourse) RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_my_course;
    }

    @Override
    protected MyCoursePresenter loadPresenter() {
        return new MyCoursePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.loadMyCourseData();
    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.iv_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void loadMyCourseDataSucceed(MyCourseBean detailBean) {
        LogUtils.i(detailBean.toString());
        mRecyclerView.setAdapter(new BaseAdapter<MyCourseBean.DataBean.ListBean>(mContext, R.layout.item_my_course, detailBean.getData().getList()) {
            @Override
            protected void convert(ViewHolder viewHolder, MyCourseBean.DataBean.ListBean listBean, int i) {
                ImageView view = viewHolder.getView(R.id.iv_photo);
                ImageUtil.setImageView(view, listBean.getPhoto_image());
                viewHolder.setText(R.id.tv_title, listBean.getCourse_name());
                View convertView = viewHolder.getConvertView();
                convertView.setOnClickListener(view1 -> {
                    Intent intent = new Intent(mContext, VideoPlayActivity.class);
                    intent.putExtra(ActionConstant.EXTRA_COURSE_ID, listBean.getCourse_id());
                    intent.putExtra(ActionConstant.EXTRA_SPEC_ID, listBean.getSpec_id());
                    startActivity(intent);
                });
            }
        });

    }
}
