package com.soul.rxandroid.conditionl_boolean;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import rx.Observable;
import rx.Subscriber;


/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/1 11:30
 */

public class DefaultIfEmptyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("empty");
        mLButton.setOnClickListener(e -> emptyObserver().subscribe(i -> log("empty:" + i)));
        mRButton.setText("noEmpty");
        mRButton.setOnClickListener(e -> notEmptyObserver().subscribe(i -> log("noEmpty:" + i)));
    }

    /**
     * 判断Observable 是否发射过，没有则传递10
     *
     * @return
     */
    private Observable<Integer> emptyObserver() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onCompleted();
            }
        }).defaultIfEmpty(10);
    }

    private Observable<Integer> notEmptyObserver() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        }).defaultIfEmpty(10);
    }

}
