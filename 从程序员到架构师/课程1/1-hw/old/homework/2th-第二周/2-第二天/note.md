# 第二天课程

```
java kotlin 相互调用
```


## 1 新建gradle项目

> 新建项目
```
Creat New Project
选择 Gradle, lib按需勾选 java/kotlin
填写组织/机构/版本
Gradle 用默认配置就行，若出现 java_home not defiend 按上面的配置下java_home，
勾选auto-import，选择本地的gradle目录
选择项目名和位置

如果遇到了卡住的诡异问题，关闭IDE，重启电脑

修改 build.gradle
ext.kotlin_version = '1.1.4-2' // 自己的版本

```

> 等自动build完成功之后，会生成类似如下目录
```
zain/ 项目名
|_ .gradle/
|_ .idea
|_ build
|_ gradle
|_ out
...

如果只有.idea说明build失败了，如果卡了建议重启电脑

可以右键 MarkDir 标记目录类型
```

> 建立代码目录
```
zain/
|_ src/
|  |_ main/
|  |  |_ java/
|  |  |_ kotlin/
|  |  |_ groovy/
|  |_ test/
|
...
```

> 构建项目
```
打开任务工具 View -> Tool Windows -> Gradle

运行 build -> build
```

> 自定义任务
```
修改 build.gradle
apply plugin: 'application' // 用于运行
mainClassName = 'BootstrapKt' // 声明入口
```

> 运行run任务
```
application -> run
或者在终端 gradle run
```

> gradle 插件
```
https://plugins.gradle.org/

使用 shadow
优点：会查找依赖，打到一个包里

修改 build.gradle
ext.shadow_version = '2.0.0'
classpath "com.github.johnrengelman.plugin-shadow:$shadow_version"
apply plugin: 'com.github.johnrengelman.plugin-shadow' // 用于打包
```

> 打包
```
因为kotlin需要运行时指定一个jar包，
所以将kt.class打成jar包
```

> war包
```
J2EE体系中放到web容器中运行
```

## 使用vertx

> lamda 表达式
```
可以称之为接口函数
接口之中只有一个函数
一般都提供的是抽象方法
```