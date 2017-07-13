package com.soul.androidcompilptions.rxandretrofi.ui.picture;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.utils.ImageUtil;
import com.soul.library.base.BaseRxActivity;
import com.soul.library.utils.LogUtils;
import com.soul.library.widget.photoview.PhotoView;

import java.util.List;

import butterknife.BindView;

import static com.soul.androidcompilptions.rxandretrofi.config.ExtraConstant.EXTRA_POSITION;

public class PictureActivity extends BaseRxActivity<PicturePresenter> implements PictureContract.View, ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private PictureAdapter mPictureAdapter;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_picture;
    }

    @Override
    protected PicturePresenter loadPresenter() {
        return new PicturePresenter();
    }

    @Override
    protected void initView() {
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initData() {
        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        mPresenter.showLocalData(position);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showLocalData(List<MeiZhi> data, int position) {
        mPictureAdapter = new PictureAdapter(this, data);
        mViewPager.setAdapter(mPictureAdapter);
        mViewPager.setCurrentItem(position);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void showMoreData(MeiZhiBean meiZhiBean) {
        List<MeiZhi> results = meiZhiBean.results;
        if (results != null && results.size() > 0) {
            mPictureAdapter.addData(results);
            mPictureAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        LogUtils.i("Tag", i + "");
        if (i > mPictureAdapter.getDataSize() - 2) {
            //加载更多数据
            mPresenter.loadMoreData();
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private class PictureAdapter extends PagerAdapter {

        private Context mContext;
        private List<MeiZhi> mData;

        public PictureAdapter(Context context, List<MeiZhi> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public int getCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            MeiZhi meiZhi = mData.get(position);
            //            View view = View.inflate(mContext, R.layout.item_pricture, null);
            //            view.findViewById(R.id.iv_photo);
            PhotoView photoView = new PhotoView(mContext);
            photoView.enable();
            ImageUtil.setImageView(photoView, meiZhi.url);
            container.addView(photoView);
            photoView.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_POSITION, position);
                setResult(10, intent);
                finish();
            });
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public int getDataSize() {
            return mData.size();
        }

        public void addData(List<MeiZhi> meiZhiBean) {
            mData.addAll(meiZhiBean);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
