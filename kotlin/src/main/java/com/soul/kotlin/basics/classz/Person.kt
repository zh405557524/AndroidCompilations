package com.soul.kotlin.basics.classz

import android.util.Log

/**
 * Description: TODO
 * Author: 祝明
 * CreateDate: 2022/9/21 19:58
 * UpdateUser:
 * UpdateDate: 2022/9/21 19:58
 * UpdateRemark:
 */
open class Person {//open 才能被继承

    val TAG = this::class.java.simpleName

    var name = ""
    var age = 0

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }


    fun printInfo() {
        Log.i(TAG, "$name's age is $age")
    }

}