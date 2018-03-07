package com.gyzh.zain.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserVerticle extends AbstractVerticle {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private SQLClient client;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        JsonObject postgreSQLClientConfig = new JsonObject()
                .put("host", "localhost")
                .put("port", 5444)
                .put("username", "postgres")
                .put("password", "zain5444")
                .put("database", "acct");
        client = PostgreSQLClient.createNonShared(vertx, postgreSQLClientConfig);

        client.getConnection(res -> {
            if (res.succeeded()) {
                log.info("user 数据库连接已建立");

                // 注册事件监听
                vertx.eventBus().consumer("com.gyzh.vertx.web.user.add", this::addUser);
                vertx.eventBus().consumer("com.gyzh.vertx.web.user.get", this::getUser);
                vertx.eventBus().consumer("com.gyzh.vertx.web.user.del", this::getUser);
                vertx.eventBus().consumer("com.gyzh.vertx.web.user.update", this::getUser);

                startFuture.complete();
            } else {
                startFuture.fail(res.cause());
            }
        });
    }

    private Future<List<String>> runSql (String sql) {
        Future<List<String>> future = Future.future();
        client.getConnection(res -> {
            if (res.failed()) {
                future.fail(res.cause());
            }

            SQLConnection connection = res.result();

            log.info("执行sql：" + sql);
            connection.query(sql, qres -> {
                connection.close();
                if (qres.succeeded()) {
                    log.info("执行sql结果: " + qres.result()
                            .getResults().toString());
                    List<String> result = qres.result()
                            .getResults()
                            .stream()
                            .map(json -> json.getString(0))
                            .sorted()
                            .collect(Collectors.toList());
                    future.complete(result);
                } else {
                    future.fail(res.cause());
                }
            });
        });
        return future;
    }

    private void getUser(Message<JsonObject> message) {
        log.info("收到获取用户消息" + message.body().toString());
        String querySql = "SELECT * FROM \"user\" " +
                "WHERE account = '" + message.body().getString("account") + "' " +
                "AND password = '" + message.body().getString("password") + "' ";

        runSql(querySql).compose(result ->
            message.reply(new JsonObject().put("user", new JsonArray(result))), null);
    }

    private void addUser(Message<JsonObject> message) {
        log.info("收到添加用户消息" + message.body().toString());
        String insertUserSql = "INSERT INTO \"user\" (id, account, password) VALUES(" +
                "'" + UUID.randomUUID() + "'" +
                ", '" + message.body().getString("account") + "'" +
                ", '" + message.body().getString("password") + "'" +
                ") " +
                "RETURNING id";
        runSql(insertUserSql).compose(result ->
                message.reply(new JsonObject().put("user", new JsonArray(result))), null);
    }



    private void reportQueryError(Message<JsonObject> message, Throwable cause) {
        log.error("执行sql异常", cause);
        message.fail(500, cause.getMessage());
    }

}
