package com.gyzh.zain.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.templ.JadeTemplateEngine;
import org.parboiled.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RestVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(RestVerticle.class);
    private static Map<String, JsonObject> users = new HashMap<>();

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);
        users.put("1", new JsonObject().put("acct", "adsadasd"));
        users.put("2", new JsonObject().put("acct", "asasd"));


        // 配置基础路由
        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        // 配置通用处理
        router.route().handler(this::handleEvery);
        // 配置模版引擎
        router.getWithRegex(".+\\.jade").handler(handlerGetJade);

        // 配置首页访问
        router.get("/").handler(this::handleIndex);
        router.get("/index").handler(this::handleIndex);
        // 登录处理
        router.post("/loginhandler").handler(this::handleLogin);
        // 注册处理
        router.route("/loguphandler").handler(this::handleLogup);
        // 登出处理
        router.route("/logouthandler").handler(this::handleLogout);

        // 配置user路由
        router.get("/user/:acctount").handler(this::handleGetUser);
        router.put("/user/:acctount").handler(this::handleAddUser);
        router.get("/users").handler(this::handleListUsers);

        // 配置静态路由
        router.route().handler(StaticHandler.create());
        // 注册处理异常
        router.route().failureHandler(this::handlerFail);

        // 启动服务
        vertx.createHttpServer().requestHandler(router::accept).listen(8882, res -> {
            if (res.succeeded()) {
                log.info("已启动端口监听 8882");
                startFuture.complete();
            } else {
                startFuture.fail(res.cause());
            }
        });
    }



    TemplateHandler handlerGetJade = TemplateHandler.create(JadeTemplateEngine.create());

    private void handleEvery(RoutingContext context) {
        context.response().putHeader("Content-type", "text/html;charset=UTF-8");
        log.info("调用了通用服务: " + context.request().path());
        context.next();
    }

    private void handleIndex(RoutingContext context) {
        log.info("调用了主体访问");
        // 登录验证
        Cookie cookie = context.getCookie("user");

        // 未登录
        if (null == cookie) {
            log.info("用户未登录");
            context.reroute("/login.jade");
            return;
        }
        // 已登录
        log.info("用户已登录" + cookie.getValue());
        context.reroute("/index.jade");
    }

    private void handleLogin(RoutingContext context) {
        HttpServerResponse response = context.response();
        HttpServerRequest request = context.request();

        log.info(request.params().toString());

        String account = request.getParam("in_account");
        String password = request.getParam("in_password");

        log.info("调用登录处理服务");
        log.info("account: " + account);
        log.info("password: " + password);

        // 数据校验
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            context.fail(new RuntimeException("empty account or password"));
            return;
        }

        JsonObject user = new JsonObject();
        user.put("account", account);
        user.put("password", password);

        vertx.eventBus().send("com.gyzh.vertx.web.user.get"
                , user, reply -> {
            if (reply.succeeded()) {
                JsonObject body = (JsonObject) reply.result().body();
                log.info("获取用户结果" + body.toString());

                if (null == body.getJsonArray("user")
                        || body.getJsonArray("user").isEmpty()) {
                    response.end("登录失败，未找到该用户或密码不对" + account);
                    return;
                }

                String id = body.getJsonArray("user").getString(0);

                context.addCookie(Cookie.cookie("user", id));
                context.put("user", body.getJsonArray("user").getString(3));

                response.end("登录成功");
            } else {
                log.info("获取用户失败");
                context.fail(reply.cause());
            }
        });
    }

    private void handleLogup(RoutingContext context) {
        HttpServerResponse response = context.response();
        HttpServerRequest request = context.request();

        String account = request.getParam("up_account");
        String password = request.getParam("up_password");

        log.info("调用注册处理服务");
        log.info("account: " + account);
        log.info("password: " + password);

        // 数据校验
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            context.fail(new RuntimeException("empty account or password"));
            return;
        }

        JsonObject user = new JsonObject();
        user.put("account", account);
        user.put("password", password);

        vertx.eventBus().send("com.gyzh.vertx.web.user.add"
                , user, reply -> {
                    if (reply.succeeded()) {
                        JsonObject body = (JsonObject) reply.result().body();
                        log.info("添加用户结果" + body.toString());
                        response.end("注册成功");
                    } else {
                        log.info("添加用户失败");
                        context.fail(reply.cause());
                    }
                });
    }

    private void handleLogout(RoutingContext context) {
        HttpServerResponse response = context.response();
        log.info("调用登出处理服务");
        context.clearUser();
        response.putHeader("location", "/").setStatusCode(302).end();
    }

    private void handleGetUser(RoutingContext context) {
        String acctount = context.request().getParam("acctount");
        HttpServerResponse response = context.response();
        if (acctount == null) {
            sendError(400, response);
        } else {
            JsonObject product = users.get(acctount);
            if (product == null) {
                sendError(404, response);
            } else {
                response.putHeader("content-type", "application/json").end(product.encodePrettily());
            }
        }
    }

    private void handleAddUser(RoutingContext context) {
        String acctount = context.request().getParam("acctount");
        HttpServerResponse response = context.response();
        if (acctount == null) {
            sendError(400, response);
        } else {
            JsonObject product = context.getBodyAsJson();
            if (product == null) {
                sendError(400, response);
            } else {
                users.put(acctount, product);
                response.end();
            }
        }
    }

    private void handleListUsers(RoutingContext context) {
        JsonArray arr = new JsonArray();
        users.forEach((k, v) -> arr.add(v));
        context.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
    }

    private void handlerFail(RoutingContext context) {
        HttpServerResponse response = context.response();
        response.setStatusCode(500)
                .putHeader("Content-type", "text/html;charset=UTF-8")
                .end("处理异常");
    }

    private void sendError(int statusCode, HttpServerResponse response) {
        response.setStatusCode(statusCode).end();
    }
}
