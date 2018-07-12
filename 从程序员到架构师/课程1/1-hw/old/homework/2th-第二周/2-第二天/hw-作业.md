# 第二天作业

## 使用vertx的路由，启动两个页面

> 普通
![Alt text](./img/普通路由页面.png)

> 异常
![Alt text](./img/异常路由页面.png)

## 代码段

> Bootstap.kt
```
package com.gyzh.zain.vertx.web

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import com.gyzh.zain.vertx.web.handler.*
import java.util.*

val vertx = Vertx.vertx()

fun main (args: Array<String>) {

    var server = vertx.createHttpServer()
    var router = createRouter()

    server.requestHandler({ router.accept(it) }).listen(8080)
}

/**
 * 路由创建器
 */
private fun createRouter() = Router.router(vertx).apply {
    get("/").handler(rootHandler)
    get("/count").handler(countHandler)
    get("/count/1").handler({ req ->
        req.fail(403)
    })
    get("/*").failureHandler({ frc ->
        var statusCode = frc.statusCode() // 获取异常码
        var response = frc.response() // 获取响应

        var errText = arrayOf("不好意思，您找错了"
                , "您在转转吧"
                , "还是没找对"
                , "这个地方不存在")
        var rNo = Random().nextInt(10)
        System.out.println("no: " + rNo)
        var no = rNo % 4
        var text = errText[no]
        System.out.println("text: " + text)
        response.putHeader("Content-type", "text/html;charset=UTF-8");
        response.setStatusCode(statusCode).end(text)
    })
}
```

> build.gradle
```
group 'com.gyzh.zain.lg.first'
version '1.0-SNAPSHOT'

buildscript { // 构建项目时用的
    ext {
        kotlin_version = '1.1.4-2'
        shadow_version = '2.0.1'
        vertx_version = '3.4.2'
    }

    repositories {
        jcenter()
    }

    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath "com.github.jengelman.gradle.plugins:shadow:$shadow_version"
    }
}

apply plugin: 'groovy'
apply plugin: 'java'
apply plugin: 'kotlin'
apply plugin: 'application' // 用于运行
apply plugin: 'com.github.johnrengelman.shadow' // 用于打包

sourceCompatibility = 1.8
mainClassName = 'com.gyzh.zain.vertx.web.BootstrapKt' // 声明入口

// 下面是项目中用的

repositories {
    jcenter()
    maven {
        url "https://oss.sonatype.org/content/repositories/iovertx-3684/"
    }
}

dependencies {
    compile "io.vertx:vertx-core:$vertx_version"
    compile "io.vertx:vertx-web:$vertx_version"

    compile "org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlin_version"

    compile 'org.codehaus.groovy:groovy-all:2.3.11'

    testCompile group: 'junit', name: 'junit', version: '4.12'
}


compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
```


