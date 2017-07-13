package com.soul.broadcastreceiverdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.soul.broadcastreceiverdemo.receiver.NeijianReceiver;

/**
 * 发送有序广播
 */
public class GuowuyuanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guowuyuan);

    }


    public void sendGift(View view){

        Intent intent = new Intent();
        //设置动作
        //receiverPermission 接收者需要声明的权限
        //resultReceiver 结果接收者
        //scheduler handler
        //initialCode 编号
        //initalData 初始化数据
        //initalExras 额外的数据 map
        intent.setAction("com.soul.guowuyuan.FFL");
        //intent 发送意图s
        sendOrderedBroadcast(intent,
                null,
                new NeijianReceiver(),
                null,
                1,
                "中央下发粮食，大家每人1000斤粮食",
                null
                );
        Toast.makeText(this,"主席万岁，粮食下发完毕",Toast.LENGTH_SHORT).show();

    }


}
