package com.soul.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.soul.library.utils.UIUtils;
import com.soul.servicedemo.service.IMusicService;
import com.soul.servicedemo.service.MusicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView mTv_current;
    private ListView mLv;
    private Intent mIntent;
    private MyConn mMyConn;
    private TimerTask mTimerTask;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        mIntent = new Intent(this, MusicService.class);
        startService(mIntent);
        mMyConn = new MyConn();
        bindService(mIntent, mMyConn, BIND_AUTO_CREATE);

        mTv_current = (TextView) findViewById(R.id.tv_current);
        mLv = (ListView) findViewById(R.id.lv);
        final List<MusicInfo> infos = new ArrayList<>();

        infos.add(new MusicInfo("小苹果", "http://192.168.1.100:8080:xpg.mps"));
        infos.add(new MusicInfo("月亮之上", "http://192.168.1.100:8080:xpg.mps"));
        infos.add(new MusicInfo("小苹果", "http://192.168.1.100:8080:xpg.mps"));
        infos.add(new MusicInfo("小苹果", "http://192.168.1.100:8080:xpg.mps"));


        mLv.setAdapter(new ArrayAdapter<MusicInfo>(this, android.R.layout.simple_list_item_1, infos));

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBinder.callPlay(infos, i);
                int position = mBinder.callGetCurrentPosition();

            }
        });


        mTimer = new Timer();
        mTimerTask = new TimerTask() {

            private int mPosition;

            @Override
            public void run() {
                mPosition = mBinder.callGetCurrentPosition();
                UIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        mTv_current.setText("正在播放：" + infos.get(mPosition).getName());
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask, 200, 5000);

    }

    private IMusicService mBinder;

    class MyConn implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //绑定成功，获取服务返回的中间人
            mBinder = (IMusicService) iBinder;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mMyConn);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask == null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }
}
