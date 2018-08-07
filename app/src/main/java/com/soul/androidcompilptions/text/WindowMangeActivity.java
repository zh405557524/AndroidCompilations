package com.soul.androidcompilptions.text;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.utils.UIUtils;

public class WindowMangeActivity extends Activity {

    private static final int OVERLAY_PERMISSION_REQ_CODE = 0x001;
    private WindowManager.LayoutParams wmParams;
    private WindowManager mWindowManager;
    private LinearLayout mFloatLayout;
    private AudioManager mAudioManager;
    private Manager mManager;
    private ComponentName mbCN;

    @Override
    protected void onStart() {
        super.onStart();
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //            if (!Settings.canDrawOverlays(this)) {
        //                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        //                        Uri.parse("package:" + getPackageName()));
        //                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        //            }
        //        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_mange);


        findViewById(R.id.bt_addView).setOnClickListener(view -> {
            //            //获取LayoutParams对象
            //            wmParams = new WindowManager.LayoutParams();
            //
            //            //获取的是LocalWindowManager对象
            //            mWindowManager = this.getWindowManager();
            //            Log.i(TAG, "mWindowManager1--->" + this.getWindowManager());
            //            //mWindowManager = getWindow().getWindowManager();
            //            Log.i(TAG, "mWindowManager2--->" + getWindow().getWindowManager());
            //
            //            //获取的是CompatModeWrapper对象
            //            //mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
            //            Log.i(TAG, "mWindowManager3--->" + mWindowManager);
            //            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            //            wmParams.format = PixelFormat.RGBA_8888;
            //            ;
            //            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            //            wmParams.gravity = Gravity.LEFT | Gravity.TOP;
            //            wmParams.x = 0;
            //            wmParams.y = 0;
            //            wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            //            wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //
            //            LayoutInflater inflater = this.getLayoutInflater();//LayoutInflater.from(getApplication());
            //
            //            mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);
            //            mWindowManager.addView(mFloatLayout, wmParams);
            if (null == mManager) {
                mManager = new Manager();
            }
            mAudioManager = (AudioManager) UIUtils.getContext().getSystemService(Context.AUDIO_SERVICE);
            mbCN = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
            int audioFocus = mAudioManager.requestAudioFocus(mManager, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (mAudioManager != null) {
                mAudioManager.registerMediaButtonEventReceiver(mbCN);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case OVERLAY_PERMISSION_REQ_CODE:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!Settings.canDrawOverlays(this)) {
                            Toast.makeText(WindowMangeActivity.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                        } else {
                            // TODO: 18/1/7 已经授权
                        }
                    }
                    break;
            }
        }
    }


    private class Manager implements AudioManager.OnAudioFocusChangeListener {

        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS: {
                }
                break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                }
                break;
                case AudioManager.AUDIOFOCUS_GAIN:

                    break;
            }
        }

    }
}
