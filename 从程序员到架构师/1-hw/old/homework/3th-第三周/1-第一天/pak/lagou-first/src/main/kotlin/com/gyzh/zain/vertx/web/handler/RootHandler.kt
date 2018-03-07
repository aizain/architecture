package com.gyzh.zain.vertx.web.handler

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

val rootHandler = Handler<RoutingContext> { req ->
    req.response().end("Hello Welcome To Onzain!")
}