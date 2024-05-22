package com.soul.kotlin.basics.basices

import android.util.Log

/**
 * Description: 基本数据类型
 * Author: zhuMing
 * CreateDate: 2020/7/2 11:52
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/2 11:52
 * UpdateRemark:
 */
class DataType {

    val TAG = DataType::class.java.simpleName

    fun main(args: Array<String>?) {
        //1、比较两个数字
        run {
            //  === 表示比较对象地址，==表示比较两个值大小
            val a = 100
            Log.i(TAG, "a===a:" + (a === a))

            val b: Int? = a
            val c: Int? = a
            Log.i(TAG, "b===c:" + (b === c))//？有疑问
            Log.i(TAG, "b==c:" + (b == c))

        }
        cutLine()
        //2、类型转换
        run {
            val a = 100

            val b = a.toByte()
            val c = a.toChar()
            val d = a.toDouble()
            val f = a.toFloat()
            val l = a.toLong()
            val s = a.toString()
        }

        // 3、位操作符
        run {

            //            shl(bits) – 左移位 (Java’s <<)
//            shr(bits) – 右移位 (Java’s >>)
//            ushr(bits) – 无符号右移位 (Java’s >>>)
//            and(bits) – 与
//            or(bits) – 或
//            xor(bits) – 异或
//            inv() – 反向
        }


        //4、数组
        kotlin.run {
            val a = arrayOf(1, 2, 3, 4)
            val b = Array(3, { i -> i * 4 })
            Log.i(TAG, "a:" + a[0])
            Log.i(TAG, "b:" + b[1])
        }
        cutLine()

        //5、字符串
        kotlin.run {
            val s = "今天天气很好"
            for (c in s) {
                Log.i(TAG, "c:" + c)
            }

            val test = """
                黄河之水
                /天上来
                """
            Log.i(TAG, "test:" + test)
        }
    }


    //分割线
    fun cutLine() {
        Log.i(TAG, "-------------------------------------------------------------------------")
    }


}