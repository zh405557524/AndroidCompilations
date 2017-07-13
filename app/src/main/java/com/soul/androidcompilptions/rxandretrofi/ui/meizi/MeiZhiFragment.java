package com.soul.androidcompilptions.rxandretrofi.ui.meizi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.ui.gan.GanKActivity;
import com.soul.library.base.BaseFragment;
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
public class MeiZhiFragment extends BaseFragment<MeiZhiPresenter> implements
        MeiZhiContract.ShowNetPhotoView, MeiZhiPhotoAdapter.Listener {

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
    protected MeiZhiPresenter loadPresenter() {
        return new MeiZhiPresenter();
    }

    @Override
    protected View initView(ViewGroup container) {
        return View.inflate(getContext(), R.layout.fragment_meizhi, null);
    }


    @Override
    protected void initData() {
        meiZhiRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        long timeMillis = System.currentTimeMillis();
        List<MeiZhi> localData = mPresenter.getLocalData();
        if (localData != null) {
            initAdapterHeaderAndFooterWrapper(localData);
        }
        setRefresh(true);
        LogUtils.i("time:" + (System.currentTimeMillis() - timeMillis));
        loadMeiZiData();
    }

    /**
     * 初始化 adapter
     *
     * @param localData
     */
    private void initAdapterHeaderAndFooterWrapper(List<MeiZhi> localData) {
        mMeiZhiCommonAdapter = new MeiZhiPhotoAdapter(mActivity, R.layout.item_meizhi, localData, this);

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
        Intent intent = new Intent(mActivity, PhotoActivity.class);
        intent.putExtra(PhotoActivity.URL, meiZhi.url);
        startActivity(intent);
    }

    @Override
    public void onGanKClick(MeiZhi meiZhi) {
        Intent intent = new Intent(mActivity, GanKActivity.class);
        intent.putExtra(GanKActivity.EXTRA_GAN_K_DATE, meiZhi.publishedAt);
        startActivity(intent);
    }

}
















