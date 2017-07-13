package com.soul.androidcompilptions.rxandretrofi.ui.gan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.entity.GanK;
import com.soul.library.base.BaseFragment;

import java.util.List;

import butterknife.BindView;


public class GanKFragment extends BaseFragment<GanKPresenter> implements GanKContract.GanKView {

    private static final String ARG_YEAR = "year";
    private static final String ARG_MONTH = "month";
    private static final String ARG_DAY = "day";
    private LayoutInflater mInflater;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.rv_recyclerView)
    public RecyclerView mRecyclerView;
    @BindView(R.id.ab_appBarLayout)
    public AppBarLayout mAppBarLayout;
    private GanKListAdapter mGanKListAdapter;

    public static Fragment newInstance(int year, int month, int day) {
        GanKFragment ganKFragment = new GanKFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_YEAR, year);
        bundle.putInt(ARG_MONTH, month);
        bundle.putInt(ARG_DAY, day);
        ganKFragment.setArguments(bundle);
        return ganKFragment;
    }

    @Override
    protected GanKPresenter loadPresenter() {
        return new GanKPresenter();
    }

    @Override
    protected View initView(ViewGroup container) {
        mInflater = LayoutInflater.from(mActivity);
        View view = mInflater.inflate(R.layout.fragment_gan_k, container, false);
        return view;
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        Bundle arguments = getArguments();
        int year = (int) arguments.get(ARG_YEAR);
        int month = (int) arguments.get(ARG_MONTH);
        int day = (int) arguments.get(ARG_DAY);
        setToolBar(year + "-" + month + "-" + day);
        mPresenter.loadGanKData(year, month, day);
    }


    @Override
    protected void initEvent() {

    }

    private void setToolBar(String date) {
        mToolbar.setTitle(date);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.return_bg_selector);
        mToolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    public void loadDataSucceed(List<GanK> ganKList) {
        mGanKListAdapter = new GanKListAdapter(ganKList);
        mRecyclerView.setAdapter(mGanKListAdapter);
    }
}
