package com.soul.library.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.library.utils
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/2/22 13:23
 */

public class PermissionsUtils {
    //申请权限的回调
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    public static void lacksPermissions(Activity activity, String... permissions) {
        ArrayList<String> strings = new ArrayList<>();
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                strings.add(permission);
            }
        }
        String[] strings1 = new String[strings.size()];
        String[] strings2 = strings.toArray(strings1);
        openPermissions(activity, strings2);
    }

    private static boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(UIUtils.getContext(), permission) == PackageManager.PERMISSION_DENIED;
    }


    private static void openPermissions(Activity context, String[] strings) {
        if (strings != null && strings.length > 0) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(context, strings,
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

}
