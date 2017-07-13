package com.soul.androidcompilptions.rxandretrofi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.soul.androidcompilptions.R;
import com.soul.library.base.BaseRxActivity;

import butterknife.BindView;

/**
 * 干货 集中营
 */
public class RxRetrofitActivity extends BaseRxActivity<RxRetrofitPresenter> implements
        RxRetrofitContract.ShowNetPhotoView {

    @BindView(R.id.vp_viewpager)
    ViewPager mViewPager;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ContentAdapter contentAdapter = new ContentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(contentAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }


    /**
     * 内容的适配类
     */
    private class ContentAdapter extends FragmentStatePagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            return ContentFragmentFactory.getFragmentCount();
        }
    }


}
















