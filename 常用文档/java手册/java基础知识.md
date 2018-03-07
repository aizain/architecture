# java基础知识

## 开发环境

> 常见名称 
```
JRE=JVM+java系统类库
JDK=JRE+编译、运行等开发环境

JRE：java运行环境（运行的最小环境）
JDK：java开发工具包（开发的最小环境)
JVM：java虚拟机

IDE：集成开发环境，eclipse是最主流的IDE
eclipse为IBM公司的，开源的
eclipse是基于插件的
```

> 入手代码 
`hello.java` 
```
package day01; //包名

public class HelloWorld {  //类名
    //main方法，程序的入口
    public static void main(String[] args) {
        //输出语句
        System.out.println("HelloWord");
        System.out.println("HelloEveryone");
    }
}
```


> 变量
```
1.变量是一个代词，代指内存中的数据
    变量是可以改变的量---代指不同的数据
2.变量必须先声明，再使用
        语法：数据类型   变量名;
                    int            a    ;
        可以一条语句声明多个同类型变量
        eg：int a,b,c;
            int a=1,b,c=1;

数据在内存中变量开辟的空间里装着
编译错误---语法错误（java的规则）


变量命名：
字母、数字、“_”、“&”
首字母不为数字
大小写敏感
不能使用java保留字
中文名可以，不提倡

见名知意，驼峰命名法    javaScore

初始化：
java使用之前必须给值，c语言可以不给系统默认给随机值

在变量声明时初始化
```

> 注释
```
//单行注释
/**/多行注释
```


> java的基本类型
```
整数类型：byte、short、int、long
浮点类型：float、double
char
boolean

M兆、KB千字节、B字节、Bit位
int------4B字节
long----8B字节
double----8B字节
float----4B字节
char----4B字节    //无符号

int a = 100    //100就是直接量，不可超出范围
int d = 10000000000000;    //后面也错了，默认直接量的整数都是int类型

溢出：
负溢出变正，正溢出变负

long：长整型
long b = 1000000000000l    //表示直接量时，以“L”或“l”结尾

BigInterger    //无限大

System.currentTimeMillis();    //返回19701月1日零时到此时此刻所经历的毫秒数
//返回long类型
1s = 1000ms
1年=365天=365*24小时= 365*24*60分钟=365*24*60*60s
=31536000000 ms

long time = System.currentTimeMillis();
System.out.println(time);

double：
默认的浮点（小数）直接量为double型，
float类型直接量，后面必须加“f”或“F”

3.14;314;0.1;.5
1.25E2 --- 1.25*10*10

舍入误差：
2进制无法精确的表示1/10，
double money = 3.0;
double x = 2.9;
System.out.println(money-x);    //0.10000000009

财务系统ERP：数字敏感，不能用double型
BigDecimal------精确表示任何小数

char：
Unicode字符集编码
'一'    //单引号里面只能放一个字符
'/u4e2d'    //Unicode表示方式，16进制

转义：
'\n' 回车
'\r' 换行
'\\' 反斜杠
'\''    单引号
'\'''    双引号

'0'码48  'a'码97  'A'码65

boolean：
true false
```

> 基本类型间的转换
```
自动转换 从小类型到大类型
强制转换 从大类型到小类型 会出现精度丧失和溢出
从小到大顺序如下，byte,short,int,long,float,double

1.整数直接量可以直接赋值给byte,char,short
2.byte,char,short参与运算时，一律转为int
```



