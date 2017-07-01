package com.soul.androidcompilptions.rxandretrofi;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.ui.gan.GanKActivity;
import com.soul.library.base.BaseRxActivity;
import com.soul.library.ui.PhotoActivity;
import com.soul.library.utils.LogUtils;
import com.soul.library.widget.RatioImageView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

import butterknife.BindView;

import static com.soul.androidcompilptions.rxandretrofi.config.ExtraConstant.EXTRA_POSITION;

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
    private LoadMoreWrapper mLoadMoreAdapter;


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
        //        meiZhiRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        meiZhiRecyclerView.setLayoutManager(new LinearLayoutManager(RxRetrofitActivity.this));
        //        getView().setSystemUiVisibility(
        //                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        //                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        //                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
            //            getView().setSystemUiVisibility(
            //                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            //                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            //                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            //                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            //                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (data != null) {
                int position = data.getIntExtra(EXTRA_POSITION, 0);
                mPresenter.showLocalData(position);
            }
        }
    }

    /**
     * 初始化 adapter
     *
     * @param localData
     */
    private void initAdapterHeaderAndFooterWrapper(List<MeiZhi> localData) {
        mMeiZhiCommonAdapter = new MeiZhiPhotoAdapter(mContext, R.layout.item_meizhi, localData, this);

        //        mLoadMoreAdapter = mMeiZhiCommonAdapter.getLoadMoreAdapter();
        meiZhiRecyclerView.setAdapter(mMeiZhiCommonAdapter.getLoadMoreAdapter());

        //上拉加载
        mMeiZhiCommonAdapter.setOnLoadMoreListener(() -> mPresenter.loadMeiZiData(++page, true));
    }

    @Override
    protected void initEvent() {
        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(() -> loadMeiZiData());
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
            mMeiZhiCommonAdapter.notifyDataChanged();
        }
    }

    @Override
    public void loadDataFailure(Throwable e) {
        e.printStackTrace();
        setRefresh(false);
    }

    @Override
    public void showLocalData(List<MeiZhi> localData, int position) {
        loadDataSucceed(localData, false);
        meiZhiRecyclerView.smoothScrollToPosition(position);
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
















