package com.soul.kotlin.basics.functionstatic

/**
 * Description: 密封类
 * Author: 祝明
 * CreateDate: 2022/9/27 10:47
 * UpdateUser:
 * UpdateDate: 2022/9/27 10:47
 * UpdateRemark:
 */

sealed  class Result1
class Success(val msg: String) : Result1()
class Failure(val error: Exception) : Result1()