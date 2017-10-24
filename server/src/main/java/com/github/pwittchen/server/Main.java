package com.github.pwittchen.server;

import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String args[]) {
    // exemplary curl request: curl "localhost:8080/sensor?add=123,456,789"

    HttpServer server = Vertx
        .vertx()
        .createHttpServer();

    server
        .requestStream()
        .toFlowable()
        .subscribe(request -> {
          logger.info("{} {}", request.rawMethod(), request.absoluteURI());
          request.response().end("request received");
        });

    server
        .rxListen(8080)
        .subscribe(httpServer -> logger.info("server is running..."));
  }
}
