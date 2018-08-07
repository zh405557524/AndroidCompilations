package com.soul.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class BaseActivity extends AppCompatActivity {

    protected Button mLButton, mRButton;
    protected TextView mResultView;
    protected String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mLButton = (Button) findViewById(R.id.left);
        mRButton = (Button) findViewById(R.id.right);
        mResultView = (TextView) findViewById(R.id.result);
        TAG = getLocalClassName();
        mLButton.setOnClickListener(this::onClickLeft);
        mRButton.setOnClickListener(this::onClickRight);
    }


    protected void log(Object s) {
        Log.d(TAG, String.valueOf(s));
        Observable.just(s).observeOn(AndroidSchedulers.mainThread()).subscribe(i -> {
            mResultView.setText(mResultView.getText() + "\n" + i);
        });
    }



    protected void onClickLeft(View view) {

    }

    protected void onClickRight(View view) {

    }
}
