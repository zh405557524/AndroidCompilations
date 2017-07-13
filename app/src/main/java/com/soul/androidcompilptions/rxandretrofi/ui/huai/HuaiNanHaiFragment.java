package com.soul.androidcompilptions.rxandretrofi.ui.huai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.audio.AudioFragment;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.cache.MyCacheActivity;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.mycourse.MyCourseActivity;
import com.soul.library.base.BaseFragment;
import com.soul.library.base.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 坏男孩资源
 */
public class HuaiNanHaiFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_viewpager)
    ViewPager mViewPager;
    private int page = 1;

    private LinkedList<Date> mData = new LinkedList<>();
    private ContentAdapter mContentAdapter;

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected View initView(ViewGroup container) {
        return View.inflate(mActivity, R.layout.fragment_huai_nan_hai, null);
    }

    @Override
    protected void initData() {
        Date date = new Date(System.currentTimeMillis());
        for (int x = 0; x < 10; x++) {
            mData.addFirst(date);
            date = getNextDay(date);
        }
        mContentAdapter = new ContentAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mContentAdapter);
        mViewPager.setCurrentItem(mData.size() - 1);
        mViewPager.addOnPageChangeListener(this);
    }

    @OnClick({R.id.rl_myCourse, R.id.rl_myCache})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_myCourse:
                mActivity.startActivity(new Intent(mActivity, MyCourseActivity.class));
                break;
            case R.id.rl_myCache:
                mActivity.startActivity(new Intent(mActivity, MyCacheActivity.class));
                break;

        }
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    private String getTimeString(Date date, String style) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(style);//
        return dateFormat.format(date);
    }


    /**
     * 获取后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (i == 0) {
            Date date = mData.get(0);
            date = getNextDay(date);
            for (int x = 0; x < 10; x++) {
                mData.addFirst(date);
                date = getNextDay(date);
            }
            ++page;
            mContentAdapter.notifyDataSetChanged();
            mViewPager.setCurrentItem(10, false);
            Log.i("Tag", "onPageSelected");
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

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
            String timeString = getTimeString(mData.get(position), "yyyy-MM-dd");
            Bundle bundle = new Bundle();
            bundle.putString("date", timeString);
            AudioFragment audioFragment = new AudioFragment();
            audioFragment.setArguments(bundle);
            return audioFragment;
        }

        @Override
        public int getCount() {
            return 10 * page;
        }
    }

}
