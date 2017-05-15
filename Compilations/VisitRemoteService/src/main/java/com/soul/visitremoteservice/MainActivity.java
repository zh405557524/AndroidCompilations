package com.soul.visitremoteservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.soul.servicedemo.IRemoteService;

public class MainActivity extends AppCompatActivity {

    private MyConn mMyConn;
    private IRemoteService mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setPackage("com.soul.servicedemo");
        mMyConn = new MyConn();
        bindService(intent, mMyConn, BIND_AUTO_CREATE);
    }


    class MyConn implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = IRemoteService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }


    public void callMethod(View view) {

        try {
            mBinder.callMethodInService();
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mMyConn);
    }
}
