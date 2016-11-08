package com.soul.androidcompilptions.customview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.soul.androidcompilptions.R;

public class StickyNavActivity extends AppCompatActivity {
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPage;

    private String[] mTitles = new String[]{"简介","评价","相关"};
    private TabFragment[] mFragment = new TabFragment[mTitles.length];
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_nav);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPage = (ViewPager) findViewById(R.id.id_stickynavalyout_viewpager);

    }


    private void initData() {
        mIndicator.setTitles(mTitles);

        for (int i = 0;i<mTitles.length ;i++){
            mFragment[i] = TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragment[position];
            }
        };

        mViewPage.setAdapter(mAdapter);
        mViewPage.setCurrentItem(0);
    }


    private void initEvent() {

        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
