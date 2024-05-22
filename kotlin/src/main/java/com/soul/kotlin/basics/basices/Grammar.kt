package com.soul.kotlin.basics.basices

import android.util.Log

/**
 * Description: kotlin语法
 * Author: zhuMing
 * CreateDate: 2020/6/28 18:15
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/28 18:15
 * UpdateRemark:
 */
class Grammar {

    val TAG: String = Grammar::class.java.simpleName


    fun main(args: Array<String>?) {
        //1、变量
        run {
            var a = 1
            val b: Int //不赋值需要定义类型
            b = 1
            a = 10
            val c = 3//val 不可改变
            log(a)
            log(b)
            log(c)
        }
        cutLine()

        //2、函数
        run {
            val sum = sum(1, 2)
            log(sum)
        }
        cutLine()
        //3、字符串模板
        run {
            var a = 1
            val s1 = "a is $a"
            Log.i(TAG, s1)
            a = 2
            val s2 = "${s1.replace("is", "was")},but now is $a   '$'a"
            Log.i(TAG, s2)
        }
        cutLine()

        //4、NULL检查机制
        run {
            //类型后面加?表示可为空
            var age: String? = "23"
            //抛出空指针异常
            val ages = age!!.toInt()
            //不做处理返回 null
            val ages1 = age?.toInt()
            //age为空返回-1
            val ages2 = age?.toInt() ?: -1
        }
        cutLine()

        // 5、类型检查
        run {
            getStringLength("fdsafds")
            getStringLength(3431243)
        }

        cutLine()
        //7、区间
        run {
            // 区间表达式由具体操作符形式 `..` 的rangeTo 函数辅以 in 和！in形成。
            for (i in 1..4) Log.i(TAG, "i:" + i)//输出 1 2 3 4
            cutLine()
            for (i in 4..1) Log.i(TAG, "I:" + i)//什么都不输出
            cutLine()
            for (i in 1..10) Log.i(TAG, "i:" + i)
            cutLine()
            //使用step
            for (i in 1..4 step 2) Log.i(TAG, "i:" + i) //输出1 3
            cutLine()
            for (i in 4 downTo 1 step 2) Log.i(TAG, "i:" + i) //输出4 2
            cutLine()
            //使用 until 函数排除结束元素
            for (i in 1 until 10) Log.i(TAG, "i:" + i)
            cutLine()
        }
        cutLine()


    }

    fun getStringLength(obj: Any): Int {

        if (obj is String) {
            Log.i(TAG, "a是字符串：" + obj.length)
        }

        if (obj !is String) {
            Log.i(TAG, "a不是字符串：" + obj.toString())
        }
        return 0
    }


    //函数，Int 返回值
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //无返回值函数
    fun log(a: Int, c: Int) {
        Log.i(TAG, a.toString() + c.toString())
    }

    fun log(a: Int) {
        Log.i(TAG, a.toString())
    }

    //分割线
    fun cutLine() {
        Log.i(TAG, "-------------------------------------------------------------------------")
    }

}