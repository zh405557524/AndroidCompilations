package com.soul.kotlin.basics

import android.util.Log
import android.view.View
import com.soul.kotlin.basics.basices.Control
import com.soul.kotlin.basics.basices.DataType
import com.soul.kotlin.basics.basices.Grammar
import com.soul.kotlin.basics.classz.Student
import com.soul.kotlin.basics.classz.Study
import com.soul.lib.test.ButtonTextFragment

/**
 * Description: kotlin基础测试类
 * Author: zhuMing
 * CreateDate: 2020/6/28 18:53
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/28 18:53
 * UpdateRemark:
 */
public class BasicsTestFragment : ButtonTextFragment(), View.OnClickListener {

    val TAG = this::class.java.simpleName

    override fun getClassName(): String {
        return "kotlin基础"
    }

    override fun initEvent() {
        addTextName("基础语法", this)
        addTextName("基本数据类型", this)
        addTextName("控制语句", this)
        addTextName("集合测试类", this)
        addTextName("Lambda 表达式测试类", this)
        addTextName("Java函数式API的使用", this)
        addTextName("判空处理", this)
        addTextName("let函数", this)
        addTextName("内嵌表达式", this)
        addTextName("函数的参数默认值", this)
    }


    override fun onClick(v: View?) {

        when (v?.getTag()) {
            "基础语法" -> Grammar().main(null)
            "基本数据类型" -> DataType().main(null)
            "控制语句" -> Control().main()
            "集合测试类" -> list()
            "Lambda 表达式测试类" -> lambda()
            "Java函数式API的使用" -> functionApi()
            "判空处理" -> nullPointer()
            "let函数" -> letFunction(Student())
            "内嵌表达式" -> embedded()
            "函数的参数默认值" -> functionParams()
        }
    }


    private fun functionParams() {
        log(str = "1234")
        log(1, "456")
    }

    private fun log(value: Int = 100, str: String) {
        Log.i(TAG, "num is ${value} ,str is ${str}")
    }

    private fun embedded() {
        var name = "20"
        var age = 8
        Log.i(TAG, "My name is ${if (1 > 2) "zjm" else "ljn"}.I am ${age} ")
    }

    private fun letFunction(study: Study?) {
        study?.let {  //此时靠?.则保证了study肯定不为空，才会执行let函数
            //it为study
            it.doHomework()
            it.readBooks()
        }
    }

    private fun nullPointer() {
        doStudy(null)
        doStudy(Student())

    }

    private fun doStudy(study: Study?) {
        study?.readBooks()//? 为null不执行
        study?.doHomework()

//        study!!.readBooks()//假设此时为空抛出异常，则和java一样
//        study.doHomework()
    }


    private fun list() {
        //list
        //1、常规创建
        val list = ArrayList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)

        //2、listOf 不可变,后续不可添加删除，只能查
        val listOf = listOf<Int>(1, 2, 3, 4)
        val get = list.get(1)
        Log.i(TAG, "get:" + get)

        //3、mutableListOf
        val mutableListOf = mutableListOf<Int>(1, 2, 3, 4, 5)
        mutableListOf.add(2)

        //map
        //1、常规
        val map = HashMap<String, String>()
        map.put("1", "2jm")
        map.put("2", "ljm")
        Log.i(TAG, "map:" + map["2"])
        //2、不可变
        val mapOf = mapOf("1" to "zjm", "2" to "ljn")
        Log.i(TAG, "mapOf:" + map["2"])
        //3、可变
        val mapMutable = mutableMapOf("1" to "zjm", "2" to "ljn")
        mapMutable["3"] = "lsb"
        Log.i(TAG, "mapMutable:" + mapMutable["3"])
    }

    private fun lambda() {
        val list = listOf<String>("a", "adb", "aabb", "a")
        val lambda = { str: String -> str.length }
//        var maxStr = list.maxBy(lambda)
//        Log.i(TAG, "maxStr:" + maxStr)
//        maxStr = list.maxBy({ str: String -> str.length })
//        Log.i(TAG, "maxStr:" + maxStr)
//        maxStr = list.maxBy() { str: String -> str.length }
//        maxStr = list.maxBy { str: String -> str.length }
//        Log.i(TAG, "maxStr:" + maxStr)
//        maxStr = list.maxBy { str -> str.length }
//        Log.i(TAG, "maxStr:" + maxStr)
//        maxStr = list.maxBy { it.length }
//        Log.i(TAG, "maxStr:" + maxStr)
        val newList = list.map { it.toUpperCase() } //将集合中的元素都准换成大写
        val newList1 = list.filter { it.length > 3 } //筛选出长度大于3的元素
        val isAny = list.any { it.length > 10 }//返回false
        val isAll = list.all { it.length > 0 }//返回true
    }

    private fun functionApi() {
        //1、线程调用
        Thread(object : Runnable {
            override fun run() {
                Log.i(TAG, "functionApi test")
            }
        }).start()
        Thread(Runnable { Log.i(TAG, "functionApi test") }).start()
        Thread({ Log.i(TAG, "functionApi test") }).start()
        Thread { Log.i(TAG, "functionApi test") }.start()

    }
}