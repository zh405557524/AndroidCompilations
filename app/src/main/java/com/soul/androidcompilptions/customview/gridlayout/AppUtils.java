package com.soul.androidcompilptions.customview.gridlayout;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class AppUtils {
    //1. 获取rom的剩余空间

    /**
     * @return rom的剩余空间 单位byte
     */
    public static long getAvailRom() {
        long size = 0;
        File dataDirectory = Environment.getDataDirectory();
        //byte
        size = dataDirectory.getFreeSpace();

        return size;
    }

    //2. 获取sd卡的剩余空

    /**
     * @return sd的剩余空间 单位byte
     */
    public static long getAvailSD() {
        long size = 0;
        File dataDirectory = Environment.getExternalStorageDirectory();
        //byte
        size = dataDirectory.getFreeSpace();

        return size;
    }

    //3. 所有安装的app信息

    /**
     * @param context
     * @return 手机中所有安装的app信息
     */
    public static List<AppInfo> getAllApps(Context context) {
        List<AppInfo> appInfos = new ArrayList<AppInfo>();
        //获取所有安装的app
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(0);
        //循环取数据，封装数据
        AppInfo appInfo;

        for (PackageInfo packageInfo : packages) {
            //信息的封装
            appInfo = new AppInfo();

            //封装包名
            appInfo.setPackName(packageInfo.packageName);
            //app的图标
            Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
            appInfo.setIcon(icon);

            //app的名字
            CharSequence appName = packageInfo.applicationInfo.loadLabel(pm);

            appInfo.setAppName(appName + "");

            //app的安装目录
            String appPath = packageInfo.applicationInfo.sourceDir;
            appInfo.setAppPath(appPath);

            //app的大小 单位是byte
            File file = new File(appPath);
            appInfo.setAppSize(file.length());

            //安装位置
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                appInfo.setRom(false);//sd卡
            } else {
                appInfo.setRom(true);//手机中
            }

            //app的类型
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                appInfo.setSystem(true);//系统app
            } else {
                appInfo.setSystem(false);//用户app
            }

            //uid
            appInfo.setUid(packageInfo.applicationInfo.uid + "");

            //添加对象
            appInfos.add(appInfo);
        }
        return appInfos;
    }

    /**
     * 获取用户app
     *
     * @param context
     * @return
     */
    public static List<AppInfo> getUserApps(Context context) {
        List<AppInfo> appInfos = new ArrayList<AppInfo>();
        //获取所有安装的app
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(0);
        //循环取数据，封装数据
        AppInfo appInfo;

        for (PackageInfo packageInfo : packages) {
            //信息的封装
            appInfo = new AppInfo();

            //封装包名
            appInfo.setPackName(packageInfo.packageName);
            //app的图标
            Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
            appInfo.setIcon(icon);

            //app的名字
            CharSequence appName = packageInfo.applicationInfo.loadLabel(pm);

            appInfo.setAppName(appName + "");

            //app的安装目录
            String appPath = packageInfo.applicationInfo.sourceDir;
            appInfo.setAppPath(appPath);

            //app的大小 单位是byte
            File file = new File(appPath);
            appInfo.setAppSize(file.length());

            //安装位置
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                appInfo.setRom(false);//sd卡
            } else {
                appInfo.setRom(true);//手机中
            }

            //uid
            appInfo.setUid(packageInfo.applicationInfo.uid + "");

            //app的类型
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                appInfo.setSystem(true);//系统app
            } else {
                appInfo.setSystem(false);//用户app
                //添加对象
                appInfos.add(appInfo);
            }

        }
        return appInfos;
    }


    /**
     * 获取系统app
     *
     * @param context
     * @return
     */
    public static List<AppInfo> getSystemApps(Context context) {
        List<AppInfo> appInfos = new ArrayList<AppInfo>();
        //获取所有安装的app
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(0);
        //循环取数据，封装数据
        AppInfo appInfo;

        for (PackageInfo packageInfo : packages) {
            //信息的封装
            appInfo = new AppInfo();

            //封装包名
            appInfo.setPackName(packageInfo.packageName);
            //app的图标
            Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
            appInfo.setIcon(icon);

            //app的名字
            CharSequence appName = packageInfo.applicationInfo.loadLabel(pm);

            appInfo.setAppName(appName + "");

            //app的安装目录
            String appPath = packageInfo.applicationInfo.sourceDir;
            appInfo.setAppPath(appPath);

            //app的大小 单位是byte
            File file = new File(appPath);
            appInfo.setAppSize(file.length());

            //安装位置
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                appInfo.setRom(false);//sd卡
            } else {
                appInfo.setRom(true);//手机中
            }

            //uid
            appInfo.setUid(packageInfo.applicationInfo.uid + "");

            //app的类型
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                appInfo.setSystem(true);//系统app
                appInfos.add(appInfo);
            } else {
                appInfo.setSystem(false);//用户app
                //添加对象
            }

        }
        return appInfos;
    }

    /**
     * 对app进行分类
     *
     * @param appInfos
     * @return
     */
    public static Map<String, List<AppInfo>> classifyAppinfos(List<AppInfo> appInfos) {

        Map<String, List<AppInfo>> stringListMap = new TreeMap<>();
        String temp = "";
        List<AppInfo> appInfoList = null;
        for (AppInfo appInfo : appInfos) {
            String pinyin = PinyinUtil.getPinyin(appInfo.getAppName()).charAt(0) + "";
            if (temp.toLowerCase().trim().equals(pinyin.toLowerCase().trim())) {
                appInfoList.add(appInfo);
            } else {
                if (appInfoList!=null){
                    stringListMap.put(temp.toLowerCase(), appInfoList);
                }
                appInfoList = new ArrayList<>();
                appInfoList.add(appInfo);
                temp = pinyin;
            }
        }
        if (temp.toLowerCase().equals("z")){
            stringListMap.put("z", appInfoList);
        }else {
            stringListMap.put("☆", appInfoList);
        }
        return stringListMap;

    }

}
