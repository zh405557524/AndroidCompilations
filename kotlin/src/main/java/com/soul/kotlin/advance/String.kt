package com.soul.kotlin.advance

/**
 * Description: 函数扩展
 * Author: 祝明
 * CreateDate: 2022/9/27 13:58
 * UpdateUser:
 * UpdateDate: 2022/9/27 13:58
 * UpdateRemark:
 */
val String.value: Int get() = 10

fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) count++
    }
    return count
}

