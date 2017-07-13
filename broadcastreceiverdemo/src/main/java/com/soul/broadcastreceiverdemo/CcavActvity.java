package com.soul.broadcastreceiverdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 发送无序广播
 */
public class CcavActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccav_actvity);
    }


    public void sendBroadcast(View view){
        //发广播
        Intent intent = new Intent();
        intent.setAction("com.soul.ccav.XWLB");
        intent.putExtra("news","这里是中央人民广播电台，正在播放晚间新闻，欢迎收听");
        sendBroadcast(intent);
    }
}
