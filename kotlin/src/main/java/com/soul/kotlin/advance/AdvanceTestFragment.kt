package com.soul.kotlin.advance

import android.content.ContentValues
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import com.soul.lib.test.ButtonTextFragment

/**
 * Description: Kotlin 进阶
 * Author: 祝明
 * CreateDate: 2022/9/27 13:53
 * UpdateUser:
 * UpdateDate: 2022/9/27 13:53
 * UpdateRemark:
 */
class AdvanceTestFragment : ButtonTextFragment(), View.OnClickListener {

    val TAG = "AdvanceTestFragment"

    override fun getClassName(): String {
        return "Kotlin 进阶"
    }

    override fun initEvent() {
        addTextName("扩展", this)
        addTextName("运算符重载", this)
        addTextName("高阶函数", this)
        addTextName("内联函数", this)
        addTextName("SharedPreferences使用优化", this)
        addTextName("ContentValues使用优化", this)
        addTextName("泛型", this)
    }

    override fun onClick(v: View?) {
        when (v?.getTag()) {
            "扩展" -> extend()//对类的方法进行补充，动态的给类添加方法
            "运算符重载" -> operator()//运算符重载是拓展运算符的功能，比如对象可使用运算符操作
            "高阶函数" -> functionHigh()//一个函数接收另一个函数作为参数，或者返回值是另一个函数，那此函数则为高阶函数.
            "内联函数" -> functionInline()//一个函数接收另一个函数作为参数，或者返回值是另一个函数，那此函数则为高阶函数.
            "SharedPreferences使用优化" -> sharedPreferences()//一个函数接收另一个函数作为参数，或者返回值是另一个函数，那此函数则为高阶函数.
            "ContentValues使用优化" -> contentValues()//一个函数接收另一个函数作为参数，或者返回值是另一个函数，那此函数则为高阶函数.
            "泛型" -> genericity()//
            "委托" -> entrust()//委托是一种设计模式，基本理念是：操作对象自己不会去处理某段逻辑，而是会把工作委托给另外一个辅助对象去处理。

        }
    }

    /**
     * 委托
     */
    private fun entrust() {

    }

    /**
     * 泛型
     */
    private fun genericity() {
        val myClass = MyClass<Int>()
        myClass.setValue(123)
        myClass.method("1")
        myClass.method(1)
        myClass.method1(1)

    }

    private fun contentValues() {
        val values = cvOf("a" to 1, "b" to "c", "d" to true)
//        db.insert("person", null, values)
    }

    private fun sharedPreferences() {
//        getSharedPreferences("data", Context.MODE_PRIVATE).open {
//            putString("name", "zjm")
//            putInt("name", 21)
//            putBoolean("isBoy", true)
//        }
    }

    fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.block()
        editor.apply()
    }

    fun cvOf(vararg pairs: Pair<String, Any?>): ContentValues {
        val cv = ContentValues()
        for (pair in pairs) {
            val key = pair.first
            val value = pair.second
            when (value) {
                is Int -> cv.put(key, value)
                is Long -> cv.put(key, value)
                is Short -> cv.put(key, value)
                is Float -> cv.put(key, value)
                is Double -> cv.put(key, value)
                is Boolean -> cv.put(key, value)
                is String -> cv.put(key, value)
                is Byte -> cv.put(key, value)
                is ByteArray -> cv.put(key, value)
                null -> cv.putNull(key)
            }
        }
        return cv
    }

    private fun functionInline() {
        var a = num1AndNum2(1, 2) { a, b -> a + b }
    }

    inline fun test(lambda1: () -> Unit, noinline lambda2: () -> Unit) {
        lambda1()
        lambda2()
    }

    inline fun runRunnable(crossinline block: () -> Unit) {
        Runnable() {
            block()
        }.run()
    }

    private fun functionHigh() {
//        Log.i(TAG, "加法：" + num1AndNum2(3, 2, ::plus))
//        Log.i(TAG, "加法：" + num1AndNum2(3, 2, ::minus))
        num1AndNum2(1, 2) { num1, num2 -> num1 - num2 }
        num1AndNum2(3, 2) { num1, num2 -> num1 + num2 }

        val rs = StringBuilder().bulid {
            append("abc")
        }
        Log.i(TAG, "rs:$rs")

    }


    fun test(func: (String, Int) -> Unit) {
        func("123", 1)
    }

    fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Unit) = operation(num1, num2)

    fun plus(num1: Int, num2: Int) = num1 + num2

    fun minus(num1: Int, num2: Int) = num1 - num2

    private fun operator() {
        val money = Money(10) + Money(30)
        Log.i(TAG, "money:$money")
        //其上述不仅只有加法运算，Kotlin所支持的可拓展运算符如下：
//        语法糖表达式	实际调用函数
//        a + b	        a.plus(b)
//        a - b	        a.minus(b)
//        a * b	        a.times(b)
//        a / b	        a.div(b)
//        a % b	        a.rem(b)
//        a++	        a.inc()
//        a–	        a.dec()
//        +a	        a.unaryPlus()
//        -a	        a.unaryMinus()
//        !a	        a.not()
//        a == b	    a.equals(b)
//        a > b
//        a < b
//        a >= b	    a.compareTo(b)
//        a <= b
//        a…b	        a.rangeTo(b)
//        a[b]	        a.get(b)
//        a[b] = c	    a.set(b, c)
//        a in b	    b.contains(a)

    }

    class Money(val value: Int) {
        operator fun plus(money: Money): Money {
            val sum = value + money.value
            return Money(sum)
        }
    }

    private fun extend() {
        val lettersCount = "132fdsafda".lettersCount()
        Log.i(TAG, "lettersCount：$lettersCount")
        val value = "".value
        Log.i(TAG, "value：$value")
    }

    /**
     * 泛型
     */
    class MyClass<T> {
        private var value: T? = null
        public fun setValue(value: T) {
            this.value = value
        }

        public fun <T> method(param: T): T {
            return param
        }

        fun <T : Number> method1(param: T): T {
            return param
        }

        fun <T> T.bulid(block: T.() -> Unit): T {
            block()
            return this
        }

        fun block() {

        }
    }


    /**
     * 委托
     */
    class MySet<T>(val helpSet: HashSet<T>) : Set<T> {

        override val size: Int
            get() = helpSet.size

        override fun contains(element: T): Boolean {
            return helpSet.contains(element)
        }

        override fun containsAll(elements: Collection<T>): Boolean {
            return helpSet.containsAll(elements)
        }

        override fun isEmpty(): Boolean {
            return helpSet.isEmpty()
        }

        override fun iterator(): Iterator<T> {
            return helpSet.iterator()
        }
    }

}