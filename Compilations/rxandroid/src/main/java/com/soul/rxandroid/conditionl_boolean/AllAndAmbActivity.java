package com.soul.rxandroid.conditionl_boolean;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;


public class AllAndAmbActivity extends BaseActivity {

    private boolean tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("all");
        mLButton.setOnClickListener(e -> allObserver().subscribe(i -> log("all:" + i)));
        mRButton.setText("amb");
        mRButton.setOnClickListener(e -> ambObserver().subscribe(i -> log("all:" + i)));
    }

    /**
     * All操作符根据一个函数对源Observable发射的所有数据进行判断，最终返回的结果就是这个判断结果。
     * 这个函数使用发射的数据作为参数，内部判断所有的数据是否满足我们定义好的判断条件，如果全部都满足则返回true，否则就返回false。
     * <p>
     * ---全部为true 返回true 否则为false
     *
     * @return
     */
    private Observable<Boolean> allObserver() {
        Observable<Integer> just;
        if (tag) {
            just = Observable.just(1, 2, 3, 4, 5);
        } else {
            just = Observable.just(1, 2, 3, 4, 5, 6);
        }
        tag = true;
        return just.all(integer -> integer < 6);
    }

    /**
     * Amb操作符可以将至多9个Observable结合起来，让他们竞争。
     * 哪个Observable首先发射了数据（包括onError和onComplete)就会继续发射这个Observable的数据，其他的Observable所发射的数据都会别丢弃。
     * <p>
     * -----最先发射的数据，会发射出去，其他则被丢弃
     *
     * @return
     */
    private Observable<Integer> ambObserver() {
        Observable<Integer> delay3 = Observable.just(1, 2, 3).delay(3000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay2 = Observable.just(4, 5, 6).delay(2000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay1 = Observable.just(7, 8, 9).delay(1000, TimeUnit.MILLISECONDS);
        return Observable.amb(delay1, delay2, delay3);
    }


}
