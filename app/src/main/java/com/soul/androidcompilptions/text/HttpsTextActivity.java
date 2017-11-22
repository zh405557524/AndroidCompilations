package com.soul.androidcompilptions.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.api.DrakeetFactory;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpsTextActivity extends AppCompatActivity {

    private Button mBtLoad;
    private TextView mHttpsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https_text);
        initView();
        initData();
    }


    private void initView() {
        mBtLoad = (Button) findViewById(R.id.bt_load);
        mHttpsInfo = (TextView) findViewById(R.id.tv_https_info);
    }

    private void initData() {
        mBtLoad.setOnClickListener(v -> {
            DrakeetFactory.getTextHttpsResource().getTextData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            mHttpsInfo.setText(e.getMessage());
                        }

                        @Override
                        public void onNext(Object o) {

                        }
                    });

        });

    }


}
