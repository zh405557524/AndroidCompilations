package com.soul.rxandroid.conditionl_boolean;

import android.os.Bundle;
import android.view.View;

import com.soul.rxandroid.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @描述：这两个操作符都是根据条件来跳过一些数据
 * @作者：祝明
 * @项目名:AndroidCompilations
 * @创建时间：2018/8/1 19:37
 */

public class SkipUntilSkipWhileActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("skipUntil");
        mRButton.setText("skipWhile");
    }

    @Override
    protected void onClickLeft(View view) {
        skipUntilObserver().subscribe(i -> log("skipUntil" + i));
    }

    @Override
    protected void onClickRight(View view) {

        skipWhileObserver().subscribe(i -> log("skipWhile" + i));
    }

    /**
     * SkipUnitl是根据一个标志Observable来判断的，当这个标志Observable没有发射数据的时候，
     * 所有源Observable发射的数据都会被跳过；当标志Observable发射了一个数据，则开始正常地发射数据。
     *
     * @return
     */
    private Observable<Long> skipUntilObserver() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(3, TimeUnit.SECONDS));
    }

    /**
     * SkipWhile则是根据一个函数来判断是否跳过数据，当函数返回值为true的时候则一直跳过源Observable发射的数据；
     * 当函数返回false的时候则开始正常发射数据。
     *
     * @return
     */
    private Observable<Long> skipWhileObserver() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .skipWhile(aLong -> aLong < 5);
    }

}
