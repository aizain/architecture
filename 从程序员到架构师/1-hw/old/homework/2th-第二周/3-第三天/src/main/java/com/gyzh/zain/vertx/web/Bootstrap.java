package com.gyzh.zain.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Bootstrap extends AbstractVerticle {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));


        // Handles the actual login
        router.route("/loginhandler").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("Content-type", "text/html;charset=UTF-8");
            response.end("登陆成功！");
        });

        // 注册登出
        router.route("/logout").handler(context -> {
            context.response().putHeader("location", "/").setStatusCode(302).end();
        });

        // 注册静态文件访问，默认在resources下
        router.route().handler(StaticHandler.create());

        server.requestHandler(router::accept).listen(8881);
    }
}
