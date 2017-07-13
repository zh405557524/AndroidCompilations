package com.soul.rxandroid.conditionl_boolean;

import android.os.Bundle;

import com.soul.rxandroid.BaseActivity;

import rx.Observable;

/**
 * @描述：SequenceEqual操作符用来判断两个Observable发射的数据序列是否相同（发射的数据相同，数据的序列相同，结束的状态相同），如果相同返回true，否则返回false
 * @作者：祝明
 *
 * @创建时间：2017/7/1 11:40
 */

public class SequenceEqualActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("sequence");
        mLButton.setOnClickListener(e -> sequence().subscribe(i -> log("sequence:" + i)));
        mRButton.setText("noSequence");
        mRButton.setOnClickListener(e -> noSequence().subscribe(i -> log("noSequence:" + i)));
    }


    private Observable<Boolean> sequence() {
        return Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2, 3));
    }

    private Observable<Boolean> noSequence() {
        return Observable.sequenceEqual(Observable.just(1, 2), Observable.just(1, 2, 3));
    }

}
