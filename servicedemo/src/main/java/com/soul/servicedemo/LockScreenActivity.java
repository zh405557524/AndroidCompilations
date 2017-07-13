package com.soul.servicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.soul.servicedemo.service.ScrennService;

public class LockScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
    }


    public void startService(View view) {
        Intent intent = new Intent(this, ScrennService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
