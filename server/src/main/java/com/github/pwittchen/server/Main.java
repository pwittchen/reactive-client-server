package com.github.pwittchen.server;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO #1 apply RxJava2
//TODO #2 create REST server
//TODO #3 log sensor events
public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String args[]) {
    Vertx.vertx()
        .createHttpServer()
        .requestHandler(req -> {
          req.response().end("Hello from Vert.x");
          logger.info("{} {} {}", req.host(), req.method().name(), req.uri());
        })
        .listen(8080, handler -> {
          if (handler.succeeded()) {
            logger.info("server is running at http://localhost:8080");
          } else {
            logger.info("Failed to listen on port 8080");
          }
        });
  }
}
