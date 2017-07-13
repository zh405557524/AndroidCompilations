package com.soul.rxandroid.conditionl_boolean;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import rx.Observable;
import rx.Subscriber;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/1 11:16
 */

public class ContainsActivity extends BaseActivity {

    private boolean tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("contains");
        mLButton.setOnClickListener(e -> containsObserver().subscribe(i -> log("contains:" + i)));
        mRButton.setText("isEmpty");
        mRButton.setOnClickListener(e -> defaultObserver().subscribe(i -> log("isEmpty:" + i)));
    }

    /**
     * 判断Observable 发射数据 是否包含 contains里面的数据
     *
     * @return
     */
    private Observable<Boolean> containsObserver() {
        if (tag) {
            return Observable.just(1, 2, 3).contains(3);
        }
        tag = true;
        return Observable.just(1, 2, 3).contains(4);
    }


    /**
     * 判断Observable 是否发射过
     *
     * @return
     */
    private Observable<Boolean> defaultObserver() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onCompleted();
            }

        }).isEmpty();
    }
}
