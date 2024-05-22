package com.soul.kotlin.basics

import android.view.View
import com.soul.kotlin.basics.classz.Singleton
import com.soul.kotlin.basics.classz.Student
import com.soul.kotlin.basics.classz.UserBean
import com.soul.lib.test.ButtonTextFragment

/**
 * Description: kotlin 类创建几种类型
 * Author: 祝明
 * CreateDate: 2022/9/21 20:15
 * UpdateUser:
 * UpdateDate: 2022/9/21 20:15
 * UpdateRemark:
 */
class ClassTestFragment : ButtonTextFragment(), View.OnClickListener {
    override fun getClassName(): String {
        return "kotlin 类创建几种类型"
    }

    override fun initEvent() {
        addTextName("普通类的继承", this)
        addTextName("数据类", this)
        addTextName("单例类", this)
    }

    override fun onClick(v: View?) {
        when (v?.getTag()) {
            "普通类的继承" -> Student()
            "数据类" -> UserBean("1", "张三", "1234")
            "单例类" -> Singleton.test()
        }
    }
}