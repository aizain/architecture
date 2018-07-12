package com.gyzh.zain.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(BootstrapVerticle.class);

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        log.info("开始部署 Verticle");

        // 部署用户服务
        prepareVerticle(new UserVerticle(), "user")
        // 部署访问服务
        .compose(v -> prepareVerticle(new RestVerticle(), "rest"));

    }

    private Future<Void> prepareVerticle(Verticle verticle, String name) {
        Future<Void> future = Future.future();
        vertx.deployVerticle(verticle, res -> {
            if (res.succeeded()) {
                log.info("部署" + name + "成功");
                future.complete();
            } else { // 部署失败
                log.error("部署" + name + "失败");
                log.error(res.cause().getMessage());
                future.fail(res.cause());
            }
        });
        return future;
    }


}
