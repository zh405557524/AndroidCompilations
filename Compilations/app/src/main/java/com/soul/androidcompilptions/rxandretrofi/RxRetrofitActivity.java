package com.soul.androidcompilptions.rxandretrofi;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.contract.RxRetrofitContract;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.presenter.RxRetrofitPresenter;
import com.soul.androidcompilptions.rxandretrofi.ui.GanKActivity;
import com.soul.androidcompilptions.rxandretrofi.ui.MeiZhiPhotoAdapter;
import com.soul.library.base.BaseRxActivity;
import com.soul.library.ui.PhotoActivity;
import com.soul.library.utils.LogUtils;
import com.soul.library.widget.RatioImageView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

import butterknife.BindView;

/**
 * 干货 集中营
 */
public class RxRetrofitActivity extends BaseRxActivity<RxRetrofitPresenter> implements
        RxRetrofitContract.ShowNetPhotoView, MeiZhiPhotoAdapter.Listener {

    private int page;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.rv_meiZhiPhoto)
    RecyclerView meiZhiRecyclerView;
    private MeiZhiPhotoAdapter mMeiZhiCommonAdapter;
    private boolean mIsRequestDataRefresh = false;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    private LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_retrofit;
    }

    @Override
    protected RxRetrofitPresenter loadPresenter() {
        return new RxRetrofitPresenter();
    }

    @Override
    protected void initView() {
        meiZhiRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        getView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void initData() {

        long timeMillis = System.currentTimeMillis();
        List<MeiZhi> localData = mPresenter.getLocalData();
        if (localData != null) {
            initAdapterHeaderAndFooterWrapper(localData);
        }
        setRefresh(true);
        LogUtils.i("time:" + (System.currentTimeMillis() - timeMillis));
        loadMeiZiData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    /**
     * 初始化 adapter
     *
     * @param localData
     */
    private void initAdapterHeaderAndFooterWrapper(List<MeiZhi> localData) {
        mMeiZhiCommonAdapter = new MeiZhiPhotoAdapter(mContext, R.layout.item_meizhi, localData, this);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mMeiZhiCommonAdapter);
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        meiZhiRecyclerView.setAdapter(mLoadMoreWrapper);
        //上拉加载
        mLoadMoreWrapper.setOnLoadMoreListener(() -> mPresenter.loadMeiZiData(++page, true));
    }

    @Override
    protected void initEvent() {
        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(() -> loadMeiZiData());
    }

    @Override
    protected void otherViewClick(View view) {

    }


    @Override
    public void loadMeiZiData() {
        page = 1;
        mPresenter.loadMeiZiData(page, false);
    }

    @Override
    public void loadDataSucceed(List<MeiZhi> meiZhiBean, boolean isMoreDate) {
        setRefresh(false);
        if (mMeiZhiCommonAdapter == null) {
            initAdapterHeaderAndFooterWrapper(meiZhiBean);
        } else {
            if (isMoreDate) {
                mMeiZhiCommonAdapter.addData(meiZhiBean);
            } else {
                mMeiZhiCommonAdapter.setData(meiZhiBean);
            }
            mLoadMoreWrapper.notifyDataSetChanged();
            mMeiZhiCommonAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadDataFailure(Throwable e) {
        e.printStackTrace();
        setRefresh(false);
    }

    public void setRefresh(boolean requestDataRefresh) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            // 防止刷新消失太快，让子弹飞一会儿.
            mSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 1000);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onImageClick(MeiZhi meiZhi, RatioImageView imageView) {
        imageView.setOriginalSize(50, 50);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                RxRetrofitActivity.this, imageView, PhotoActivity.TRANSIT_PIC);
        Intent intent = new Intent(mContext, PhotoActivity.class);
        intent.putExtra(PhotoActivity.URL, meiZhi.url);
        startActivity(intent);
    }

    @Override
    public void onGanKClick(MeiZhi meiZhi) {
        Intent intent = new Intent(mContext, GanKActivity.class);
        intent.putExtra(GanKActivity.EXTRA_GAN_K_DATE, meiZhi.publishedAt);
        startActivity(intent);

    }

}
















