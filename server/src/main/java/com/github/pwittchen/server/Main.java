package com.github.pwittchen.server;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

//TODO #1 apply RxJava2
//TODO #2 create REST server
//TODO #3 log sensor events
public class Main {
  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String args[]) {
    Vertx.vertx()
        .createHttpServer()
        .requestHandler(req -> {
          req.response().end("Hello from Vert.x");
          String message = "host: %s, method: %s, uri: %s";
          logger.info(String.format(message, req.host(), req.method().name(), req.uri()));
        })
        .listen(8080, handler -> {
          if (handler.succeeded()) {
            System.out.println("server is running at http://localhost:8080/");
          } else {
            System.err.println("Failed to listen on port 8080");
          }
        });
  }
}
