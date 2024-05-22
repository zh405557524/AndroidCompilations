package com.soul.androidcontent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.soul.lib.test.TestActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_subassembly).setOnClickListener(this);
        findViewById(R.id.bt_customview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_subassembly:
                startActivity("com.soul.androidcontent.subassembly");
                break;
            case R.id.bt_customview:
                startActivity("com.soul.androidcontent.customview");
                break;
        }
    }


    private void startActivity(String packageName) {
        final Intent intent = new Intent(MainActivity.this, TestActivity.class);
        intent.putExtra("pageName", packageName);
        startActivity(intent);
    }
}
