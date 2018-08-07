package com.soul.rxandroid.conditionl_boolean;

import android.os.Bundle;
import android.view.View;

import com.soul.rxandroid.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @描述：TakeUntil和TakeWhile操作符可以说和SkipUnitl和SkipWhile操作符是完全相反的功能
 * @作者：祝明
 * @项目名:AndroidCompilations
 * @创建时间：2018/8/2 9:34
 */

public class TakeUntilTakeWhileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText("takeUntil");
        mRButton.setText("takeWhile");

    }


    @Override
    protected void onClickLeft(View view) {
        super.onClickLeft(view);
        takeUntilObserver().subscribe(i -> log("takeUntil:" + i));
    }

    @Override
    protected void onClickRight(View view) {
        super.onClickRight(view);
        takeWhileObserver().subscribe(i -> log("takeWhile:" + i));
    }


    /**
     * TakeUntil也是使用一个标志Observable是否发射数据来判断，
     * 当标志Observable没有发射数据时，正常发射数据，而一旦标志Observable发射过了数据则后面的数据都会被丢弃
     *
     * @return
     */
    private Observable<Long> takeUntilObserver() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(Observable.timer(3, TimeUnit.SECONDS));
    }

    /**
     * TakeWhile则是根据一个函数来判断是否发射数据，
     * 当函数返回值为true的时候正常发射数据；当函数返回false的时候丢弃所有后面的数据。
     *
     * @return
     */
    private Observable<Long> takeWhileObserver() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile(aLong -> aLong < 5);
    }


}
