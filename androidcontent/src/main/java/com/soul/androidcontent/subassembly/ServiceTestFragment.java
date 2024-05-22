package com.soul.androidcontent.subassembly;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.soul.androidcontent.subassembly.service.TestService;
import com.soul.lib.test.ButtonTextFragment;

import androidx.fragment.app.FragmentActivity;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Description:服务测试类
 * Author: 祝明
 * CreateDate: 2020/4/26 14:41
 * UpdateUser:
 * UpdateDate: 2020/4/26 14:41
 * UpdateRemark:
 */
public class ServiceTestFragment extends ButtonTextFragment implements View.OnClickListener {

    @Override
    public String getClassName() {
        return "service 测试类";
    }

    @Override
    protected void initEvent() {

        addTextName("启动服务", this);
        addTextName("绑定服务", this);

        addTextName("解绑服务", this);
        addTextName("停止服务", this);

    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        Intent intent = new Intent(getActivity(), TestService.class);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        switch (tag) {
            case "启动服务":
                activity.startService(intent);
                break;
            case "绑定服务":
                activity.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case "解绑服务":
                activity.unbindService(mServiceConnection);
                break;
            case "停止服务":
                activity.stopService(intent);
                break;
        }

    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("Tag", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("Tag", "onServiceDisconnected");
        }
    };

}

