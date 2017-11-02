package com.github.pwittchen.server;

import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  
  public static void main(final String args[]) {
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
}
