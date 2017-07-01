package com.soul.library.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.soul.library.mvp.IView;
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

public abstract class BaseRxActivity<P extends BasePresenter> extends RxAppCompatActivity implements IView {


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
    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

}
