package com.soul.kotlin.advance

/**
 * Description: TODO
 * Author: 祝明
 * CreateDate: 2022/9/27 16:12
 * UpdateUser:
 * UpdateDate: 2022/9/27 16:12
 * UpdateRemark:
 */

fun StringBuilder.bulid(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}