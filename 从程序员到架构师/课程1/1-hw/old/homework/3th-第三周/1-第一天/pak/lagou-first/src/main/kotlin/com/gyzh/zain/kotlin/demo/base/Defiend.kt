package com.gyzh.zain.kotlin.demo.base // 声明所在包名

import java.util.* // 导入包


// 定义函数
fun sum (a: Int, b: Int): Int {
    return a + b
}

fun nothing (a: Int, b: Int): Unit {
    // no return
}

fun nothing1 (a: Int, b: Int) {
    // no return
}

fun sum1 (a: Int, b: Int): Int? {
    return a + b
}

fun sum2 (a: Int, b: Int) = a + b


// 定义局部变量, 只能赋值一次
val out: Int = 1
val out1 = 1
// val out2: Int // 类中定义时，必须初始化

// 可变变量
var oc: Int = 1
var oc1 = 1
// var oc2: Int // 类中定义时，必须初始化


fun main (args: Array<String>) {
    // 定义局部变量, 只能赋值一次
    val param: Int = 1
    val param1 = 1
    val param2: Int // 局部变量时，可以后初始化

    // 可变变量
    var ic: Int = 1
    var ic1 = 1
    var ic2: Int
}
