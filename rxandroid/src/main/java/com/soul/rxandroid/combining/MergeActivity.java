package com.soul.rxandroid.combining;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import rx.Observable;
import rx.Subscriber;

public class MergeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("merge");
        mLButton.setOnClickListener(e -> mergeObservable().subscribe(i -> log("Merge:" + i)));
        mRButton.setText("mergeDelayError");
        mRButton.setOnClickListener(e -> mergeDelayErrorObserver().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                log("onError:" + e);
            }

            @Override
            public void onNext(Integer integer) {
                log("onNext:" + integer);
            }
        }));
    }

    /**
     * merge操作符将多个Observable发射的数据整合起来发射，
     * 就如同是一个Observable发射的数据一样。但是其发射的数据有可能是交错的，如果想要没有交错，可以使用concat操作符。
     *
     * @return
     */
    private Observable<Integer> mergeObservable() {
        return Observable.merge(Observable.just(1, 2, 3), Observable.just(4, 5, 6));
    }

    /**
     * 当某一个Observable发出onError的时候，merge的过程会被停止并将错误分发给Subscriber，
     * 如果不想让错误终止merge的过程，可以使用MergeDelayError操作符，会将错误在merge结束后再分发。
     *
     * @return
     */
    private Observable<Integer> mergeDelayErrorObserver() {
        return Observable.mergeDelayError(Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if (i == 3) {
                        subscriber.onError(new Throwable("error"));
                    }
                    subscriber.onNext(i);
                }
            }
        }), Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                for (int i = 6; i < 10; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }));
    }

}
