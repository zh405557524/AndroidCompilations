package com.soul.library.base;

import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IPresenter;
import com.soul.library.mvp.IView;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.library.base
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/11 22:53
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter {


    private CompositeSubscription mCompositeSubscription;

    private WeakReference mActReference;

    protected M mModel;
    protected V mView;


    @Override
    public void attachView(IView view) {
        mActReference = new WeakReference(view);
        mView = (V) mActReference.get();
    }


    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    @Override
    public void detachView() {
        if (mActReference != null) {
            mActReference.clear();
            mActReference = null;
        }
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

}
