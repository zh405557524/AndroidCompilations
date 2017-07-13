package com.soul.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 低功耗蓝牙开发 4.0
 */
public class PowerLowBlueActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    private BluetoothManager mBluetoothManger;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_low_blue);
        playVideo("http://192.168.10.90:8080/demo.mp4");
        if (!checkDeviceBluetooth()){
            return;
        }
        initBluetooth();
        checkBluetoothIsEnable();


    }



    private void playVideo(String videoPath){

        Intent intent = new Intent(Intent.ACTION_VIEW);

        String strend="";

        if(videoPath.toLowerCase().endsWith(".mp4")){

            strend="mp4";

        }

        else if(videoPath.toLowerCase().endsWith(".3gp")){

            strend="3gp";

        }

        else if(videoPath.toLowerCase().endsWith(".mov")){

            strend="mov";

        }

        else if(videoPath.toLowerCase().endsWith(".wmv")){

            strend="wmv";

        }



        intent.setDataAndType(Uri.parse(videoPath), "video/"+strend);

        startActivity(intent);

    }
    /**
     * 确认是否支持蓝牙
     *
     * @return
     */
    private boolean checkDeviceBluetooth() {

        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    /**
     * 初始化蓝牙管理器
     */
    private void initBluetooth() {
        mBluetoothManger = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManger.getAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "蓝牙适配器初始化失败", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * 检查蓝牙是否开启
     */
    private void checkBluetoothIsEnable() {
        if (!mBluetoothAdapter.isEnabled()) {
            //请求打开蓝牙
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BLUETOOTH);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BLUETOOTH && resultCode == RESULT_CANCELED) {
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
