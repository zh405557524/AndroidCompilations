package com.soul.kotlin.basics.classz

import android.util.Log

/**
 * Description: 主构造
 * Author: 祝明
 * CreateDate: 2022/9/21 20:04
 * UpdateUser:
 * UpdateDate: 2022/9/21 20:04
 * UpdateRemark:
 */
class Student2(val name: String, var age: Int) {

    init {
        Log.i("TAG", "name is $name")
        Log.i("TAG", "age is $age")
    }
}