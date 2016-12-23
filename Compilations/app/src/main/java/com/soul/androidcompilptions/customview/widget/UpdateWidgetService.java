package com.soul.androidcompilptions.customview.widget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class UpdateWidgetService extends Service {
    private Timer timer;
    private TimerTask task;

    public UpdateWidgetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
//                int runningTaskCount = SystemInfoUtils.getRunningTaskCount(UpdateWidgetService.this);
//                long avaliMem = SystemInfoUtils.getAvaliMem(UpdateWidgetService.this);
//                ComponentName componentName = new ComponentName(UpdateWidgetService.this, MyWidget.class);
//                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.appwidget_view);
//
//                //设置Widget中Textview的显示内容
//                remoteViews.setTextViewText(R.id.tv_runprocessnumber, "正在运行软件:" + runningTaskCount);
//                remoteViews.setTextViewText(R.id.tv_avalimem, "可用内存:" + Formatter.formatFileSize(UpdateWidgetService.this, avaliMem));
//
//                //点击widget的一键清理按钮，发送广播，在AutoKillTaskReceiver广播中杀掉所有的进程。
//                Intent intent = new Intent(UpdateWidgetService.this, AutoKillTaskReceiver.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(UpdateWidgetService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                remoteViews.setOnClickPendingIntent(R.id.btn_killall, pendingIntent);
//
//                //点击widget显示信息部分，跳到程序管理页面
//                Intent startActivityIntent = new Intent(UpdateWidgetService.this, TaskManagerActivity.class);
//                startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                PendingIntent processInfoIntent = PendingIntent.getActivity(UpdateWidgetService.this, 0, startActivityIntent, PendingIntent.FLAG_ONE_SHOT);
//                remoteViews.setOnClickPendingIntent(R.id.ll_processinfo, processInfoIntent);
//
//                //由AppWidgetManager处理Wiget。
//                AppWidgetManager awm = AppWidgetManager.getInstance(getApplicationContext());
//                awm.updateAppWidget(componentName, remoteViews);

            }
        };
        timer.schedule(task, 0, 3000);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        task.cancel();
        timer = null;
        task = null;
    }
}
