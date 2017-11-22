package com.soul.customviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.soul.customviewdemo.test.CheckView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckView mCheckView;
    private RelativeLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        mCheckView = (CheckView) findViewById(R.id.checkView);
        mRootView = (RelativeLayout) findViewById(R.id.root_view);
        findViewById(R.id.bt_check).setOnClickListener(this);
        findViewById(R.id.bt_unCheck).setOnClickListener(this);
    }

    private void initData() {
//        VoiceAnimalView voiceAnimalView = new VoiceAnimalView(MainActivity.this);
//        mRootView.addView(voiceAnimalView);

    }

    private void initEvent() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_check:
                mCheckView.check();
                break;
            case R.id.bt_unCheck:
                mCheckView.unCheck();
                break;
        }

    }
}
