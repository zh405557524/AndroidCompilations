package com.soul.rxandroid.combining;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CombineLatestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("combineList");
        mLButton.setOnClickListener(e -> combineListObserver()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> log("combineList:" + i)));
        mRButton.setText("CombineLatest");
        mRButton.setOnClickListener(e -> combineLatestObserver()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> log("CombineLatest:" + i)));
    }

    /**
     * 创建一个Observable
     *
     * @param index
     * @return
     */
    private Observable<Integer> createObserver(int index) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i < 6; i++) {
                    subscriber.onNext(i * index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    List<Observable<Integer>> list = new ArrayList<>();

    /**
     * CombineLatest操作符可以将2~9个Observable发射的数据组装起来然后再发射出来
     *
     * @return
     */
    private Observable<Integer> combineLatestObserver() {
        return Observable.combineLatest(createObserver(1), createObserver(2), (num1, num2) -> {
            log("left:" + num1 + " right:" + num2);
            return num1 + num2;
        });
    }

    /**
     * 遍历一个observable的集合
     * CombineLast操作符可以让我们直接将组装的Observable作为参数传值，也可以将所有的Observable装在一个List里面穿进去。
     *
     * @return
     */
    private Observable<Integer> combineListObserver() {
        for (int i = 0; i < 5; i++) {
            list.add(createObserver(i));
        }

        return Observable.combineLatest(list, args -> {

            int temp = 0;
            for (Object i : args) {
                log(i);
                temp += (Integer) i;
            }
            return temp;
        });
    }


}
