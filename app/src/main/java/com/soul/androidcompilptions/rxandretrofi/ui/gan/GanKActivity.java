package com.soul.androidcompilptions.rxandretrofi.ui.gan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.soul.androidcompilptions.R;
import com.soul.library.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

public class GanKActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    public static final String EXTRA_GAN_K_DATE = "gan_k_date";

    @BindView(R.id.vp_viewpager)
    public ViewPager mViewPager;
    public Date mGanKDate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gan_k;
    }

    @Override
    protected void initView() {
        mGanKDate = (Date) getIntent().getSerializableExtra(EXTRA_GAN_K_DATE);
        GanKPagerAdapter ganKPagerAdapter = new GanKPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(ganKPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class GanKPagerAdapter extends FragmentPagerAdapter {

        public GanKPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mGanKDate);
            calendar.add(Calendar.DATE,-position);
            return GanKFragment.newInstance(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        }

        @Override
        public int getCount() {

            return 5;
        }
    }
}
