package com.soul.rxandroid.combining;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import rx.Observable;
import rx.Subscriber;

public class ZipActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("zipWith");
        mLButton.setOnClickListener(e -> zipWithObserver().subscribe(i -> log("zipWith:" + i + "\n")));
        mRButton.setText("zip");
        mRButton.setOnClickListener(e -> zipWithIterableObserver().subscribe(i -> log("zipWith:" + i + "\n")));
    }

    /**
     * Zip操作符将多个Observable发射的数据按顺序组合起来，每个数据只能组合一次，而且都是有序的。最终组合的数据的数量由发射数据最少的Observable来决定。
     *
     * @return
     */
    private Observable<String> zipWithObserver() {

        return Observable.zip(createObserver(2), createObserver(3), (s, s2) -> "-" + s2);
    }

    private Observable<String> zipWithIterableObserver() {
        return Observable.zip(createObserver(2), createObserver(3), createObserver(4), (s, s2, s3) -> s + "-" + s2 + "--" + s3);
    }

    private Observable<String> createObserver(int index) {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 1; i <= index; i++) {
                    log("emitted:" + index + "--" + i);
                    subscriber.onNext(index + "-" + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
