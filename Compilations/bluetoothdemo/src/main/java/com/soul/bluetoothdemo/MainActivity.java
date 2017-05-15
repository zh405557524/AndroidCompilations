package com.soul.bluetoothdemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.soul.library.utils.LogUtils;
import com.soul.library.utils.PermissionsUtils;
import com.soul.library.utils.ThreadFactory;
import com.soul.library.utils.UIUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 经典蓝牙开发
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    //1 常量
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String message = "000001";
    private static final String lockName = "BOLUTEK";
    private static final String UUID_STR = "00001101-0000-1000-8000-00805F9B34FB";
    private static final String[] permissions = new String[]{
            Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN
    };
    //2 UI控件
    private Button mSearchBluetooth;
    private TextView mTvConnect;
    private ListView mListConnect;
    private static TextView mTvDevice;
    private static ListView mListView;

    //3 普通成员变量
    private BluetoothReceiver mBluetoothReceiver;
    private BluetoothAdapter mBluetoothAdapter;
    private static HashMap<String, BluetoothDevice> mDeviceList;
    private static ArrayList<String> mDevices;
    private static ArrayList<String> mConnectDevices;

    private BluetoothSocket mBluetoothSocket;

    private ProgressDialog mProgressDialog;

    // 4 初始化方法区
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mSearchBluetooth = (Button) findViewById(R.id.bt_searchBluetooth);
        mTvConnect = (TextView) findViewById(R.id.tv_connect);
        mListConnect = (ListView) findViewById(R.id.listView_connect);
        mTvDevice = (TextView) findViewById(R.id.tv_device);
    }

    private void initData() {
        mDeviceList = new HashMap<>();
        mDevices = new ArrayList<>();
        mConnectDevices = new ArrayList<>();

        //检查权限
        PermissionsUtils.lacksPermissions(this, permissions);
        //获取蓝牙列表
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //如果蓝牙未打开就打开蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);
        } else {
            //查询已匹配的设备
            Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                String deviceName = addDevices(bluetoothDevice, mConnectDevices);
                mDeviceList.put(deviceName, bluetoothDevice);
            }
        }
        if (mConnectDevices.size() > 0) {
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, mConnectDevices);
            mListConnect.setAdapter(stringArrayAdapter);
            mTvConnect.setVisibility(View.VISIBLE);
        } else {
            mTvConnect.setVisibility(View.GONE);
        }

        //开启蓝牙可见性
        Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        enable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600); //3600为蓝牙设备可见时间
        startActivity(enable);
    }

    private void initEvent() {
        mSearchBluetooth.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
        mListConnect.setOnItemClickListener(this);
    }


    //5 事件响应区
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             *搜索蓝牙设备
             */
            case R.id.bt_searchBluetooth:
                searchBluetooth();
                break;
        }
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {
        ThreadFactory.getNormaPool().execute(new Runnable() {
            @Override
            public void run() {
                //停止查找
                if (mBluetoothAdapter.isDiscovering()) {
                    mBluetoothAdapter.cancelDiscovery();
                }

                String deviceName = "";
                //进行连接
                if (parent.getId() == R.id.listView) {
                    deviceName = mDevices.get(position);
                } else {
                    deviceName = mConnectDevices.get(position);
                }
                BluetoothDevice device = mDeviceList.get(deviceName);
                Method method;
                try {
                    method = device.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                    mBluetoothSocket = (BluetoothSocket) method.invoke(device, 1);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                UIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        mProgressDialog = new ProgressDialog(MainActivity.this);
                        mProgressDialog.setCancelable(true);
                        mProgressDialog.setMessage("正在连接中");
                        mProgressDialog.show();
                    }
                });

                try {
                    if (mBluetoothSocket != null) {
                        mBluetoothSocket.connect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    UIUtils.postTaskSafely(new Runnable() {
                        @Override
                        public void run() {
                            mProgressDialog.dismiss();
                        }
                    });
                    if (mBluetoothSocket != null) {
                        try {
                            mBluetoothSocket.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
    }


    // 6 普通方法区

    /**
     * 显示设备
     */
    private static void showDevices() {
        if (mDevices.size() > 0) {
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(UIUtils.getContext(),
                    android.R.layout.simple_list_item_1, mDevices);
            mListView.setAdapter(stringArrayAdapter);
            mTvDevice.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 添加设备名称
     *
     * @param device
     * @param devices
     */
    private static String addDevices(BluetoothDevice device, List<String> devices) {
        String name = "";
        if (!devices.contains(device.getName())) {
            if (device.getName() != null) {
                devices.add(device.getName());
                name = device.getName();
            } else {
                if (!devices.contains(device.getAddress())) {
                    devices.add(device.getAddress());
                    name = device.getName();
                }
            }
        }
        return name;
    }

    //7 发起异步任务方法区

    /**
     * 搜索蓝牙设备
     */
    public void searchBluetooth() {
        if (mBluetoothReceiver != null) {
            unregisterReceiver(mBluetoothReceiver);
        }
        if (mDevices != null && mDevices.size() > 0) {
            mDevices.clear();
            showDevices();
        }
        //激活蓝牙搜索周围设备
        mBluetoothAdapter.startDiscovery();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        mBluetoothReceiver = new BluetoothReceiver();
        //注册监听
        registerReceiver(mBluetoothReceiver, filter);
    }

    private boolean isLock(BluetoothDevice device) {
        boolean isLockName = device.getName() != null;
        boolean isSingleDevice = !mDevices.contains(device.getName());
        return isLockName && isSingleDevice;
    }


    //8 异步回调方法区

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    // 9 生命周期
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBluetoothReceiver);
    }


    // 10 内部类

    /**
     * 蓝牙广播
     */
    private static class BluetoothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LogUtils.i("name:" + device.getName() + "------address:" + device.getAddress());
                String name = addDevices(device, mDevices);
                mDeviceList.put(name, device);
                mDevices.removeAll(mConnectDevices);
                showDevices();
            }
        }
    }


}
