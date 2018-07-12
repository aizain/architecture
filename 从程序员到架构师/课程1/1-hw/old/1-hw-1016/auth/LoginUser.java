package com.gyzh.zain.vertx.auth;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;

public class LoginUser extends AbstractUser {

    AuthProvider authProvider;

    @Override
    public JsonObject principal() {
        return null;
    }

    @Override
    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void doIsPermitted(String permission, Handler<AsyncResult<Boolean>> resultHandler) {

    }

    @Override
    public User isAuthorised(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
        return super.isAuthorised(authority, resultHandler);
    }
}
