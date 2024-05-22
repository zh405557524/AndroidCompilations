package com.soul.kotlin.basics.classz

import android.util.Log

/**
 * Description: 主构造
 * Author: 祝明
 * CreateDate: 2022/9/21 20:01
 * UpdateUser:
 * UpdateDate: 2022/9/21 20:01
 * UpdateRemark:
 */
class Student(name: String, age: Int) : Person(name, age), Study {
    var number = ""
    var grade = 0

    constructor() : this("", 0, "", 0) {

    }

    constructor(name: String, age: Int, number: String, grade: Int) : this(name, age) {
        this.number = number
        this.grade = grade
    }


    override fun study() {
        Log.i(TAG, name + "is studying")
    }

    override fun readBooks() {
        Log.i(TAG, name + "is readBooks")
    }

    override fun doHomework() {
        Log.i(TAG, name + "is doHomework")
    }

}