package com.soul.rxandroid.combining;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class StartWithSwitchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("StartWith");
        mLButton.setOnClickListener(e -> startWithObserver().subscribe(i -> log("startWith:" + i)));
        mRButton.setText("switch");
        mRButton.setOnClickListener(e -> switchObserver().subscribe(i -> log("switch:" + i)));

    }

    /**
     * StartWith操作符会在源Observable发射的数据前面插上一些数据。不仅仅只可以插入一些数据，
     * 还可以将Iterable和Observable插入进入。如果插入的是Observable，则这个Observable发射的数据会插入到
     * 源Observable发射数据的前面。
     *
     * @return
     */
    private Observable<Integer> startWithObserver() {

        return Observable.just(1, 2, 3).startWith(-1, 0);
    }

    /**
     * switch操作符在Rxjava上的实现为switchOnNext,用来将一个发射多个小Observable的源Observable转化为一个Observable，
     * 然后发射这多个小Observable所发射的数据。
     * 需要注意的就是，如果一个小的Observable正在发射数据的时候，源Observable又发射出一个新的小Observable，
     * 则前一个Observable发射的数据会被抛弃，直接发射新
     * 的小Observable所发射的数据。
     *
     * @return
     */
    private Observable<String> switchObserver() {
        return Observable.switchOnNext(Observable.create(new Observable.OnSubscribe<Observable<String>>() {
            @Override
            public void call(Subscriber<? super Observable<String>> subscriber) {
                for (int i = 1; i < 3; i++) {
                    subscriber.onNext(createObserver(i));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
    }


    private Observable<String> createObserver(int index) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 1; i < 5; i++) {
                    subscriber.onNext(index + "-" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).subscribeOn(Schedulers.newThread());
    }


}
