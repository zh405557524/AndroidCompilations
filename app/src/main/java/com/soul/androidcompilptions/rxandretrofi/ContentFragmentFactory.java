package com.soul.androidcompilptions.rxandretrofi;

import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;

import com.soul.androidcompilptions.rxandretrofi.ui.huai.HuaiNanHaiFragment;
import com.soul.androidcompilptions.rxandretrofi.ui.meizi.MeiZhiFragment;
import com.soul.library.base.BaseFragment;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/7/3 19:57
 */

public class ContentFragmentFactory {


    /**
     * fragment的集合
     */
    public static SparseArrayCompat<BaseFragment> sFragments = new SparseArrayCompat<>();
    /**
     * 妹子图
     */
    private static final int FRAGMENT_MEI_ZHI = 0;
    /**
     * 坏男孩
     */
    private static final int FRAGMENT_HAI_NAN_HAI = 1;

    public static BaseFragment getFragment(int index, Bundle bundle) {
        BaseFragment fragment = null;
        if (sFragments.get(index) != null) {
            return sFragments.get(index);
        }
        switch (index) {
            case FRAGMENT_MEI_ZHI://发现音乐
                fragment = new MeiZhiFragment();
                break;
            case FRAGMENT_HAI_NAN_HAI://坏男孩
                fragment = new HuaiNanHaiFragment();
                break;
        }

        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        sFragments.put(index, fragment);

        return fragment;
    }

    public static BaseFragment getFragment(int index) {
        return getFragment(index, null);
    }

    public static int getFragmentCount() {

        return 2;
    }


}
