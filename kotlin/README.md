# kotlin
## 一、基础语法

* 1、函数定义 

    * 函数定义使用关键字 fun,参数格式为：参数:类型
        ~~~kotlin
          fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
              return a + b
          }
        ~~~
    * 表达式作为函数体，返回类型自动推断:
        ~~~kotlin
          fun sum(a:Int,b:Int) =a+b
          public fun sum(a:Int,b:Int):Int = a+b //public 方法则必须明确写出返回类型
        ~~~
    * 无返回值的函数(类似java中的void):
         ~~~kotlin
          fun printSum(a:Int,b:Int):Unit{
              print(a+b)
          }    
          //如何是返回Unit类型，则可以省略，对于(对于public方法也是这样):
          public fun printSum(a:Int,b:Int){
              print(a+b)
          }
        ~~~
    * 可变长参数函数
         函数的变长可以用`vararg`关键字进行标识:
         ~~~kotlin
             fun vars(vararg v:int){
                 for(vt in v){
                     print(v)
                 }
             }
             vars(1,2,3,4,5) //输出12345
         ~~~
    * lambda(匿名函数)
        lambda表达式使用实例:
        ~~~kotlin
          val sumLambda:(Int,Int)->Int = {x,y ->x+y}
          println(sumLambda(1,3)) //输出 3
        ~~~
    
 * 2、定义常量与变量

    * 可变变量定义:var 关键字  
        ~ var <标识符>:<类型> = <初始值> ~
    * 不可变变量定义:val关键字，只能复制一次的变量(类似java中final修饰的变量)
        ~ val <标识符>:<类型> = <初始化值>~
     常量与变量都可以没有初始化值，但是在引用前必须初始化
     编译器支持自动类型判断，即声明时可以不制定类型，由编译器判断
     ~~~kotlin
      val a: Int = 1
      val b = 1       // 系统自动推断变量类型为Int
      val c: Int      // 如果不在声明时初始化则必须提供变量类型
      c = 1           // 明确赋值
      
      var x = 5        // 系统自动推断变量类型为Int
      x += 1           // 变量可修改
     ~~~
    
* 3、注释

    Kotlin支持单行和多行注释，实力如下:
    ~~~kotlin
      // 这是一个单行注释
      
      /* 这是一个多行的
         块注释。 */
    ~~~
    与 Java 不同, Kotlin 中的块注释允许嵌套。
    
* 4、字符串模板

    $ 表示一个变量名或者变量值
    $varName 表示变量值
    ${varName.fun()} 表示变量的方法返回值:
    ~~~kotlin
      var a = 1
      //模板中的简单名称:
      val s1 = "a is $a"
      a = 2
      //模板中的任意表达式
      val s2 = "${s1.replace("is","was")},but now is &a"
    ~~~
    原生字符串和转义字符串内部都支持模板。 如果你需要在原生字符串中表示字面值 
    $ 字符（它不支持反斜杠转义），你可以用下列语法：
    ~~~kotlin
      fun main(args: Array<String>) {
          val price = """
          ${'$'}9.99
          """
          println(price)  // 求值结果为 $9.99
      }
    ~~~
    
* 5、NULL检查机制

    Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式，
    字段后加!!像Java一样抛出空异常，另一种字段后加?可不做处理返回值为 null或配合?:做空判断处理
    ~~~kotlin
      //类型后面加?表示可为空
      var age: String? = "23" 
      //抛出空指针异常
      val ages = age!!.toInt()
      //不做处理返回 null
      val ages1 = age?.toInt()
      //age为空返回-1
      val ages2 = age?.toInt() ?: -1
    ~~~
    当一个引用可能为 null 值时, 对应的类型声明必须明确地标记为可为 null。
    当 str 中的字符串内容不是一个整数时, 返回 null:
    ~~~kotlin
      fun parseInt(str: String): Int? {
        // ...
      }
    ~~~
    以下实例演示如何使用一个返回值可为 null 的函数:
    ~~~kotlin
      fun main(args: Array<String>) {
        if (args.size < 2) {
          print("Two integers expected")
          return
        }
        val x = parseInt(args[0])
        val y = parseInt(args[1])
        // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
        if (x != null && y != null) {
          // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
          print(x * y)
        }
      }
    ~~~
    
* 6、类型检测及自动类型转换

    我们可以使用`is`运算符检测一个表达式是否某类型的一个实例(类似java中的`instanceof` 关键字)
    ~~~kotlin
      fun getStringLength(obj:Any):Int?{
          if(obj is String){
              //做过类型判断以后，obj会被系统自动转换为String类型
              return obj.length;
          }
          //在这里还有一种方法，与java中instanceof不同，使用~is
          if(obj !is String){
        
          }
          //这里的obj仍然是Any类型的引用
          return null
      }
    ~~~
    或者
    ~~~kotlin
      fun getStringLength(obj: Any): Int? {
        if (obj !is String)
          return null
        // 在这个分支中, `obj` 的类型会被自动转换为 `String`
        return obj.length
      }
    ~~~
    甚至可以
    ~~~kotlin
      fun getStringLength(obj: Any): Int? {
        // 在 `&&` 运算符的右侧, `obj` 的类型会被自动转换为 `String`
        if (obj is String && obj.length > 0)
          return obj.length
        return null
      }
    ~~~

* 7、区间

    区间表达式由具体操作符形式 `..` 的rangeTo 函数辅以 in 和！in形成。
    区间是为任何可以比较类型定义的，但对于整形原生类型，它有一个优化的实现。以下是使用区间的一个实例:
    ~~~kotlin
      for (i in 1..4) print(i) // 输出“1234”
      
      for (i in 4..1) print(i) // 什么都不输出
      
      if (i in 1..10) { // 等同于 1 <= i && i <= 10
          println(i)
      }
      
      // 使用 step 指定步长
      for (i in 1..4 step 2) print(i) // 输出“13”
      
      for (i in 4 downTo 1 step 2) print(i) // 输出“42”
      
      
      // 使用 until 函数排除结束元素
      for (i in 1 until 10) {   // i in [1, 10) 排除了 10
           println(i)
      }
    ~~~
    实例测试
    ~~~kotlin
      fun main(args: Array<String>) {
          print("循环输出：")
          for (i in 1..4) print(i) // 输出“1234”
          println("\n----------------")
          print("设置步长：")
          for (i in 1..4 step 2) print(i) // 输出“13”
          println("\n----------------")
          print("使用 downTo：")
          for (i in 4 downTo 1 step 2) print(i) // 输出“42”
          println("\n----------------")
          print("使用 until：")
          // 使用 until 函数排除结束元素
          for (i in 1 until 4) {   // i in [1, 4) 排除了 4
              print(i)
          }
          println("\n----------------")
      }
    ~~~
    输出结果:
    ~~~kotlin
    /*
      循环输出：1234
      ----------------
      设置步长：13
      ----------------
      使用 downTo：42
      ----------------
      使用 until：123
      ----------------
      */
    ~~~

## 二、基本数据类型

    Kotlin 的基本数值类型包括 Byte、Short、Int、Long、Float、Double 等。不同于 Java 的是，
    字符不属于数值类型，是一个独立的数据类型。
    Double 64,Float 32,Long 64,Int 32,Short 16,Byte 8

* 1、字面常量
    下面是所有类型的字面常量
    > 十进制：123
    > 长整型以大写的 L 结尾：123L
    > 16 进制以 0x 开头：0x0F
    > 2 进制以 0b 开头：0b00001011
	> 注意：8进制不支持

    Kotlin 同时也支持传统符号表示的浮点数值：
    > Doubles 默认写法: 123.5, 123.5e10
    > Floats 使用 f 或者 F 后缀：123.5f
    
    你可以使用下划线使数字常量更易读：
    ~~~kotlin
      val oneMillion = 1_000_000
      val creditCardNumber = 1234_5678_9012_3456L
      val socialSecurityNumber = 999_99_9999L
      val hexBytes = 0xFF_EC_DE_5E
      val bytes = 0b11010010_01101001_10010100_10010010
    ~~~

* 2、比较两个数字
    Kotlin没有基础数据类型，只有封装数据类型。定义的变量，kotlin封装了一个对象，保证不会出现空指针。
    === 表示比较对象地址，==表示比较两个值大小
    ~~~kotlin
      fun main(args: Array<String>) {
          val a: Int = 10000
          println(a === a) // true，值相等，对象地址相等
      
          //经过了装箱，创建了两个不同的对象
          val boxedA: Int? = a
          val anotherBoxedA: Int? = a
      
          //虽然经过了装箱，但是值是相等的，都是10000
          println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
          println(boxedA == anotherBoxedA) // true，值相等
      }
    ~~~
* 3、类型转换
    由于不同的表示方式，较小类型并不是较大类型的子类型，较小的类型不能隐式转换为较大的类型。
    ~~~kotlin
      val b: Byte = 1 // OK, 字面值是静态检测的
      val i: Int = b // 错误
    ~~~
    我们可以代用其toInt()方法
    ~~~kotlin
      val b: Byte = 1 // OK, 字面值是静态检测的
      val i: Int = b.toInt() // OK
    ~~~
    每种数据类型都有下面的这些方法，可以转化为其它的类型：
    ~~~kotlin
    /*
      toByte(): Byte
      toShort(): Short
      toInt(): Int
      toLong(): Long
      toFloat(): Float
      toDouble(): Double
      toChar(): Char
    */
    ~~~
    自动类型转化
    ~ val l = 1L + 3 // Long + Int => Long ~

* 4、位操作符
    对于Int和Long类型，还有一系列的位操作符可以使用，分别是：
    ~~~kotlin
    /*
      shl(bits) – 左移位 (Java’s <<)
      shr(bits) – 右移位 (Java’s >>)
      ushr(bits) – 无符号右移位 (Java’s >>>)
      and(bits) – 与
      or(bits) – 或
      xor(bits) – 异或
      inv() – 反向
      */
    ~~~
    
* 5、字符    
     Char 不能直接和数字操作，Char 必需是单引号 ' 包含起来的
     字符字面值用单引号括起来: '1'。 特殊字符可以用反斜杠转义。
      支持这几个转义序列：\t、 \b、\n、\r、\'、\"、\\ 和 \$。
     ~~~kotlin
      fun decimalDigitValue(c: Char): Int {
          if (c !in '0'..'9')
              throw IllegalArgumentException("Out of range")
          return c.toInt() - '0'.toInt() // 显式转换为数字
      }
     ~~~
    当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性。
    
* 6、布尔
    布尔用 Boolean 类型表示，它有两个值：true 和 false。
    若需要可空引用布尔会被装箱。
    内置的布尔运算有：
    ~~~kotlin
    /*
      || – 短路逻辑或
      && – 短路逻辑与
      ! - 逻辑非
      */
    ~~~

* 7、数组
    数组用类 Array 实现
    数组的创建两种方式：一种是使用函数arrayOf()；另外一种是使用工厂函数。
    ~~~kotlin
      fun main(args: Array<String>) {
          //[1,2,3]
          val a = arrayOf(1, 2, 3)
          //[0,2,4]
          val b = Array(3, { i -> (i * 2) })
      
          //读取数组内容
          println(a[0])    // 输出结果：1
          println(b[1])    // 输出结果：2
      }
    ~~~
    除了类Array，还有ByteArray, ShortArray, IntArray，用来表示各个类型的数组，
    省去了装箱操作，因此效率更高，其用法同Array一样：
    ~~~kotlin
      val x: IntArray = intArrayOf(1, 2, 3)
      x[0] = x[1] + x[2]
    ~~~

* 8、字符串
    和 Java 一样，String 是不可变的。
    方括号 [] 语法可以很方便的获取字符串中的某个字符，也可以通过 for 循环来遍历
    ~~~kotlin
      for (c in str) {
          println(c)
      }
    ~~~
    Kotlin 支持三个引号 """ 扩起来的字符串，支持多行字符串 ,可以通过 trimMargin() 方法来删除多余的空白。
    ~~~kotlin   
        fun main(args: Array<String>) {
            val text = """
            |多行字符串
            |菜鸟教程
            |多行字符串
            |Runoob
            """.trimMargin()
            println(text)    // 前置空格删除了
        }
    ~~~
    默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")。

## 三、条件控制
  
* 1、`if`表达式
    一个 if 语句包含一个布尔表达式和一条或多条语句。
    ~~~kotlin
      // 传统用法
      var max = a 
      if (a < b) max = b
      
      // 使用 else 
      var max: Int
      if (a > b) {
          max = a
      } else {
          max = b
      }
       
      // 作为表达式
      val max = if (a > b) a else b
    ~~~
    我们也可以把 IF 表达式的结果赋值给一个变量。
    ~~~kotlin
      val max = if (a > b) {
          print("Choose a")
          a
      } else {
          print("Choose b")
          b
      }
    ~~~
    ~ val c = if (condition) a else b ~
    
    使用区间
    使用 in 运算符来检测某个数字是否在指定区间内，区间格式为 x..y ：
    ~~~kotlin
      fun main(args: Array<String>) {
          val x = 5
          val y = 9
          if (x in 1..8) {
              println("x 在区间内")
          }
      }
    ~~~

* 2、`When`表达式
    when 类似其他语言的 switch 操作符。其最简单的形式如下：
    ~~~kotlin
      when (x) {
          1 -> print("x == 1")
          2 -> print("x == 2")
          else -> { // 注意这个块
              print("x 不是 1 ，也不是 2")
          }
      }
    ~~~
    when 也可以用来取代 if-else if链。
    ~~~kotlin
      when {
          x.isOdd() -> print("x is odd")
         
          x.isEven() -> print("x is even")
          else -> print("x is funny")
      }
    ~~~
    when 中使用 in 运算符来判断集合内是否包含某实例：
    ~~~kotlin
      fun main(args: Array<String>) {
          val items = setOf("apple", "banana", "kiwi")
          when {
              "orange" in items -> println("juicy")
              "apple" in items -> println("apple is fine too")
          }
      }
    ~~~





































