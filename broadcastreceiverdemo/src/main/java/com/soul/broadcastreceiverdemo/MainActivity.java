package com.soul.broadcastreceiverdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEt_ipNumber;
    private SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt_ipNumber = (EditText) findViewById(R.id.et_ipNumber);
        mSp = getSharedPreferences("config", MODE_PRIVATE);
        String ipNumber = mSp.getString("ipNumber", "");
        mEt_ipNumber.setText(ipNumber);

    }


    public void save(View view) {
        String ipNumber = mEt_ipNumber.getText().toString().trim();
        SharedPreferences.Editor edit = mSp.edit();
        edit.putString("ipNumber", ipNumber);
        edit.apply();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }
}
