package com.gyzh.zain.vertx.auth;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAuthProvider implements AuthProvider {

    private static final Logger log = LoggerFactory.getLogger(LoginAuthProvider.class);
    private Vertx vertx;

    public LoginAuthProvider(Vertx vertx){
        this.vertx=vertx;
    }

    @Override
    public void authenticate(JsonObject user, Handler<AsyncResult<User>> resultHandler) {
        Future<User> future = Future.future();
        future.setHandler(resultHandler);

        vertx.eventBus().send("com.gyzh.vertx.web.user.get", user, reply -> {
            if (reply.succeeded()) {
                User loginUser = (User) reply.result().body();
                loginUser.setAuthProvider(this);
                future.complete(loginUser);
            } else {
                future.failed();
            }
        });
    }
}
