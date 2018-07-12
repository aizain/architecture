package com.gyzh.zain.vertx.web

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import com.gyzh.zain.vertx.web.handler.*
import java.util.*

val vertx = Vertx.vertx()

fun main (args: Array<String>) {

    var server = vertx.createHttpServer()
    var router = createRouter()

    server.requestHandler({ router.accept(it) }).listen(8081)
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
