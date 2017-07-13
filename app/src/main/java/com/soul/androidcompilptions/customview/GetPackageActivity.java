package com.soul.androidcompilptions.customview;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.soul.androidcompilptions.R;

import java.util.List;

public class GetPackageActivity extends Activity {
    private TextView mTvPackgeName;
    private WindowManager.LayoutParams mWindowLayoutPara;
    private WindowManager mWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_package);
//        init();
//        initView();
//        setPackageName();
    }

    private void initView() {
        View view = View.inflate(GetPackageActivity.this, R.layout.item_packagename, null);
        mTvPackgeName = (TextView) view.findViewById(R.id.tv_packgename);
        mWindowManager.addView(view, mWindowLayoutPara);
    }

    private void setPackageName() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                    List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(1);
                    ComponentName cn = runningTasks.get(0).topActivity;
                    final String packageName = cn.getPackageName();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTvPackgeName.setText(packageName);
                        }
                    });

                    SystemClock.sleep(1000);
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler();


    private void init() {
        mWindowLayoutPara = new WindowManager.LayoutParams();
        mWindowLayoutPara.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;//定义WindowManager.LayoutParams类型,TYPE_SYSTEM_ERROR为系统内部错误提示，显示于所有内容之上
        mWindowLayoutPara.format = PixelFormat.RGBA_8888;
        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);//系统服务，注意这里必须加getApplicationContext(),否则无法把悬浮窗显示在最上层
    }
}
