package com.soul.kotlin.basics.basices

import android.util.Log

/**
 * Description: 条件控制
 * Author: 祝明
 * CreateDate: 2022/9/21 19:46
 * UpdateUser:
 * UpdateDate: 2022/9/21 19:46
 * UpdateRemark:
 */
class Control {

    val TAG = Control::class.java.simpleName

    fun main() {
        //1、if
        kotlin.run {
            val max = max(10, 11)
        }
        cutLine()
        //2、when
        kotlin.run {
            val name = "Tom"
            val score = getScore(name)
        }
        cutLine()

        // 3、for-in
        kotlin.run {
            val range = 0..10
            for (i in range) {
                Log.i(TAG, "i:$i")
            }
        }

        //4、for-step
        cutLine()
        kotlin.run {
            for (i in 0 until 10 step 2) {
                Log.i(TAG, "i:$i")
            }
        }

        //5、for-down
        cutLine()
        kotlin.run {
            for (i in 10 downTo 1) {
                Log.i(TAG, "i:$i")
            }
        }
    }

    fun max(a: Int, b: Int) = if (a > b) a else b

    fun getScore(name: String) = when (name) {
        "Tom" -> "不及格"
        "Pony" -> "不及格"
        else -> "名字非法"
    }


    //分割线
    fun cutLine() {
        Log.i(TAG, "-------------------------------------------------------------------------")
    }
}