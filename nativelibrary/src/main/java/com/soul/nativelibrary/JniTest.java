package com.soul.nativelibrary;

/**
 * Description:
 * Author: 祝明
 * CreateDate: 2020/2/19 16:09
 * UpdateUser:
 * UpdateDate: 2020/2/19 16:09
 * UpdateRemark:
 */
public class JniTest {


    static {
        System.loadLibrary("Lib");
    }

    public static String testDemo() {
        return testDemo_c();
    }

    public static void startUPDServer() {
        startUPDServer_c();
    }

    public static void sendClientData(String str) {
        sendClientData_c(str);
    }


    private static native String testDemo_c();

    /**
     * 启动服务端
     *
     * @return
     */
    private static native String startUPDServer_c();

    private static native void sendClientData_c(String str);

    public static native void sendCallJavaTest(Object test);
}
