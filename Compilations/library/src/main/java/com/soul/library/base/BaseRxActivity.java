package com.soul.library.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.soul.library.mvp.IView;
import com.soul.library.utils.LogUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;


/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.library.base
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/11 22:57
 */

public abstract class BaseRxActivity<P extends BasePresenter> extends RxAppCompatActivity implements
        View.OnClickListener,IView {


    protected View view;

    protected P mPresenter;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        mContext = BaseRxActivity.this;
        mPresenter = loadPresenter();
        ButterKnife.bind(this);
        initCommonData();
        initView();
        initData();
        initEvent();
    }


    protected abstract int getLayoutId();

    protected abstract P loadPresenter();

    protected void initCommonData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    ;

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    protected abstract void otherViewClick(View view);

    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                otherViewClick(view);
                break;
        }
    }


    /**
     * @param str 显示一个内容为str的toast
     */
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param contentId 显示一个内容为contentId指定的toast
     */
    public void toast(int contentId) {
        Toast.makeText(this, contentId, Toast.LENGTH_SHORT).show();
    }


    /**
     * @param str 日志的处理
     */
    public void LogE(String str) {
        LogUtils.e(getClass().getSimpleName(), str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

}
