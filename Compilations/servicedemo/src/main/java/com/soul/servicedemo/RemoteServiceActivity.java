package com.soul.servicedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RemoteServiceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_service);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
