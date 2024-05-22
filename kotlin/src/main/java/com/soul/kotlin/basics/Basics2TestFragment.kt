package com.soul.kotlin.basics

import android.app.Activity
import android.util.Log
import android.view.View
import com.soul.kotlin.basics.functionstatic.*
import com.soul.lib.test.ButtonTextFragment
import java.io.File

/**
 * Description: kotlin基础2
 * Author: 祝明
 * CreateDate: 2022/9/27 9:46
 * UpdateUser:
 * UpdateDate: 2022/9/27 9:46
 * UpdateRemark:
 */
class Basics2TestFragment : ButtonTextFragment(), View.OnClickListener {

    val TAG = "Basics2TestFragment"

    override fun getClassName(): String {
        return "kotlin基础2"
    }

    override fun initEvent() {
        addTextName("as关键字-类型强转", this)
        addTextName("静态函数的声明", this)
        addTextName("标准函数的使用", this)
        addTextName("懒加载-lateinit", this)
    }

    override fun onClick(v: View?) {
        when (v?.getTag()) {
            "as关键字-类型强转" -> {
                val activity = context as Activity
            }
            "静态函数的声明" -> functionStatic()
            "标准函数的使用" -> functionStandard()
            "懒加载-lateInit" -> lateInit()
            "密封类的使用-sealed" -> sealed()

        }
    }


    private fun sealed() {
        getResultMsg(Success("成功"))
    }

    fun getResultMsg(result: Result1) = when (result) {
        is Success -> result.msg
        is Failure -> result.error.message
    }

    lateinit var file: File

    private fun lateInit() {
        if (!::file.isInitialized) {
            file = File("\\sdcard\\temp")
        }
        file.name
        file.toURI()
    }

    private fun functionStandard() {
        // 1、let 辅助判断

        //2、with 接受两个参数，一个任意类型对象，一个 Lambda 表达式
        //第一个参数会传给Lambda 使用，其中Lambda内部执行的方法都是传入的对象所执行的。Lambda 的最后一行代码会当成返回值返回。调用with 则lambda 会立即执行
        run {
            val obj = "abc"
            val result = with(obj) { "value$obj" }
            Log.i(TAG, "with  result:$result")
        }

        run {
            val fruits = listOf("apple,banana,orange")
            val result = with(StringBuffer()) {
                append("Start eating fruits.\n")
                for (fruit in fruits) {
                    append(fruit).append("\n")
                }
                append("Ate all fruits")
                toString()
            }
            Log.i(TAG, "with fruits:$result")
        }

        //3、run 与with 类似 不同的是run在对象上调用
        run {
            val fruits = listOf("apple,banana,orange")
            val result = StringBuffer().run {
                append("Start eating fruits.\n")
                for (fruit in fruits) {
                    append(fruit).append("\n")
                }
                append("Ate all fruits")
                toString()
            }
            Log.i(TAG, "run fruits:$result")
        }

        //4、apply 与run 相似，不同他的返回值是调用对象本身
        run {
            val fruits = listOf("apple,banana,orange")
            val result = StringBuffer().apply {
                append("Start eating fruits.\n")
                for (fruit in fruits) {
                    append(fruit).append("\n")
                }
                append("Ate all fruits")
            }
            Log.i(TAG, "with fruits:$result")
        }

        // kotlin不需要使用findViewById
        // bulid.gradle 开头导入'kotlin-android-extensions'
        //  id 'com.android.application'
        //    id 'kotlin-android'
        //    id 'kotlin-android-extensions'

    }


    private fun functionStatic() {
        //静态方法
        Test.test2()
        Test.test3()
        //静态成员变量
        TestObject.value
    }


}