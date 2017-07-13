package com.soul.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.soul.library.utils.LogUtils;
import com.soul.servicedemo.service.IService;
import com.soul.servicedemo.service.TextService;

public class MainActivity extends AppCompatActivity {

    private Intent mIntent;
    private MyConn mMyConn = new MyConn();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, TextService.class);
    }

    /**
     * start 方式开启服务
     *
     * @param view
     */
    public void startService(View view) {

        startService(mIntent);

    }

    /**
     * 停止服务
     *
     * @param view
     */
    public void stopService(View view) {
        stopService(mIntent);
    }

    /**
     * bind方式开启服务
     * intent 意图
     * ServiceConnection 服务绑定回调
     * <p>
     * BIND_AUTO_CREATE 绑定服务，如果服务不存在，自动创建服务
     *
     * @param view
     */
    public void bindService(View view) {

        bindService(mIntent, mMyConn, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     *
     * @param view
     */
    public void unbindService(View view) {
        unbindService(mMyConn);
    }

    private IService mMyBinder;

    class MyConn implements ServiceConnection {


        /**
         * 当服务被绑定成功的时候，调用
         *
         * @param componentName
         * @param iBinder
         */
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtils.i("服务被绑定成功");
            mMyBinder = (IService) iBinder;
        }


        /**
         * 服务被解绑的时候，调用
         *
         * @param componentName
         */
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
 

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mMyConn);
    }

    public void callMethodService(View view) {
        //调用系统里的方法。
        mMyBinder.callMethodInService(3000);

    }


}
