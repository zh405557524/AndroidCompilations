package com.soul.androidcompilptions.text;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.soul.library.utils.UIUtils;

public class MediaButtonReceiver extends BroadcastReceiver {

    private static final String TAG = "MediaButtonReceiver";
    public static final String EXTRA_CMD = "musiccommand";

    public static final int EXTRA_CMD_PREVIOUS = 0x1000; // 上一首
    public static final int EXTRA_CMD_NEXT = 0x1001; // 下一首
    public static final int EXTRA_CMD_PLAY = 0x1002; // 播放
    public static final int EXTRA_CMD_PAUSE = 0x1003; // 主动暂停
    public final static String ACTION_MUSIC_CONTROL = "MusicControl_Activity_To_Service_Action";//音乐控制


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        KeyEvent keyEvent = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);


        if (Intent.ACTION_MEDIA_BUTTON.equals(action) && keyEvent.getAction() == 1) {
            // 获得按键字节码
            int keyCode = keyEvent.getKeyCode();
            if (KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE == keyCode || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
                sendMusicControl(EXTRA_CMD_PAUSE);
            } else if (KeyEvent.KEYCODE_MEDIA_NEXT == keyCode) {
                sendMusicControl(EXTRA_CMD_NEXT);
            } else if (KeyEvent.KEYCODE_MEDIA_PREVIOUS == keyCode) {
                sendMusicControl(EXTRA_CMD_PREVIOUS);
            } else if (KeyEvent.KEYCODE_MEDIA_PLAY == keyCode) {
                sendMusicControl(EXTRA_CMD_PLAY);
            }

            if (isOrderedBroadcast()) {
                abortBroadcast();
            }
        }
    }

    public static void sendMusicControl(int command) {
        Intent intent = new Intent();
        intent.setAction(ACTION_MUSIC_CONTROL);
        intent.putExtra(EXTRA_CMD, command);
        UIUtils.getContext().sendBroadcast(intent);
    }

}