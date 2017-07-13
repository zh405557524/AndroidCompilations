package com.soul.rxandroid.combining;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @描述：根据时间窗口来组合两个Observable发射的数据
 * @作者：祝明
 * @创建时间：2017/5/5 9:12
 */

public class JoinActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("join");
        mLButton.setOnClickListener(e -> joinObserver().subscribe(i -> log("join:" + i + "\n")));
        mRButton.setText("groupJoin");
        mRButton.setOnClickListener(e -> groupJoinObserver().subscribe(i -> i.subscribe(j -> log("groupJoin:" + j + "\n"))));
    }

    private Observable<String> createObserver() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 1; i < 5; i++) {
                    subscriber.onNext("Right-" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).subscribeOn(Schedulers.newThread());
    }

    /**
     * 按照Observable的时间顺序来发射Observable 顺序
     * 虽然Observable 发射了4个数据，但是源Observable 只发射了一个有效期为3秒的数据，所以
     * 最终结果只有3个数据
     *
     * @return
     */
    private Observable<String> joinObserver() {
        return Observable.just("Left-").join(createObserver(),
                integer -> Observable.timer(3000, TimeUnit.MILLISECONDS),//有效时间源
                integer -> Observable.timer(2000, TimeUnit.MICROSECONDS),//发射间隔时间
                (i, j) -> i + j);
    }

    private Observable<Observable<String>> groupJoinObserver() {
        return Observable.just("Left-")
                .groupJoin(createObserver(),
                        s -> Observable.timer(3000, TimeUnit.MICROSECONDS),
                        s -> Observable.timer(2000, TimeUnit.MICROSECONDS),
                        (s, stringObservable) -> stringObservable.map(str -> s + str));
    }

}
