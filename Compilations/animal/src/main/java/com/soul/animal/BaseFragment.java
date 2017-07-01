package com.soul.animal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;

/**
 * * @author Administrator
 *
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2015/12/30 10:42
 */
public abstract class BaseFragment extends Fragment {


    protected Activity mActivity;
    protected int state;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView(container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    // 初始化界面
    protected abstract View initView(ViewGroup container);

    // 初始化数据
    protected abstract void initData();

    // 初始化事件
    protected void initEvent() {
    }

    ;


    /**
     * 开启fragment
     *
     * @param id       容器的id
     * @param fragment 开启的fragment
     * @param bundle   传递的数据
     * @param tag      开启fragment的标签
     */
    public void startFragment(int id, Fragment fragment, Bundle bundle,
                              String tag) {
        // 开启事务
        FragmentTransaction transaction = getActivity()
                .getSupportFragmentManager().beginTransaction();
        // 设置参数
        fragment.setArguments(bundle);
        transaction.add(id, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    /**
     * 有返回结果的开启Fragment,返回的结果在onActivityResult中获取
     *
     * @param id          容器的id
     * @param fragment    开启的fragment
     * @param bundle      传递的数据
     * @param tag         开启fragment的标签
     * @param reuqestCode 请求码
     */
    public void startFragmentForResult(int id, Fragment fragment,
                                       Bundle bundle, String tag, int reuqestCode) {

        fragment.setTargetFragment(this, reuqestCode);
        startFragment(id, fragment, bundle, tag);
    }

    /**
     * 设置返回的结果,
     *
     * @param intent 存储数据的意图,在onActivityResult中获取结果
     */
    public void setResult(Intent intent) {

        getTargetFragment().onActivityResult(getTargetRequestCode(), 0, intent);
    }


    public void finish() {
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
