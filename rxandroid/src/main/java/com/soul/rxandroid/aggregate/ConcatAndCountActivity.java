package com.soul.rxandroid.aggregate;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import rx.Observable;


/**
 * 聚合
 */
public class ConcatAndCountActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("concat");
        mLButton.setOnClickListener(e -> concatObserver().subscribe(i -> log("concat:" + i)));
        mRButton.setText("count");
        mRButton.setOnClickListener(e -> countObserver().subscribe(i -> log("count:" + i)));
    }

    /**
     * 将observable的数据都集成在一起
     *
     * @return
     */
    private Observable<Integer> concatObserver() {
        Observable<Integer> obser1 = Observable.just(1, 2, 3);
        Observable<Integer> obser2 = Observable.just(4, 5, 6);
        Observable<Integer> obser3 = Observable.just(7, 8, 9);
        return Observable.concat(obser1, obser2, obser3);
    }

    /**
     * 发射的数据的个数
     *
     * @return
     */
    private Observable<Integer> countObserver() {
        return Observable.just(1, 2, 3).count();
    }
}


