package com.soul.kotlin

import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import com.soul.lib.Global

/**
 * Description: TODO
 * Author: zhuMing
 * CreateDate: 2020/6/28 20:00
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/28 20:00
 * UpdateRemark:
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Global.init(this)
    }
}