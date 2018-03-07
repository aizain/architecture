package com.gyzh.zain.vertx.web.handler

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

var count = 0;

val countHandler = Handler<RoutingContext> { req ->
    req.response().end("""count ${count++}""")
}