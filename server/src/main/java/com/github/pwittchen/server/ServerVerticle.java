package com.github.pwittchen.server;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerVerticle extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(ServerVerticle.class);

  @Override public void start() throws Exception {
    final HttpServer server = Vertx
        .vertx()
        .createHttpServer();

    server
        .requestStream()
        .toFlowable()
        .onBackpressureDrop()
        .subscribe(request -> {
          logger.info("{} {}", request.rawMethod(), request.absoluteURI());
          request.response().end("request received");
        });

    server
        .rxListen(8080)
        .subscribe(httpServer -> logger.info("server is running at port 8080..."));
  }

  @Override public void stop() throws Exception {
    logger.info("verticle stopped");
  }
}
