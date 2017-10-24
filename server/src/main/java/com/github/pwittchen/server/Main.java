package com.github.pwittchen.server;

import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String args[]) {
    //Vertx vertx = Vertx.vertx();
    //Router router = Router.router(vertx);
    //router.route().handler(BodyHandler.create());
    //
    ///*
    // * you can call this with:
    // * curl localhost:8080/
    // */
    //router.route("/").method(HttpMethod.GET).handler(routingContext -> {
    //      HttpServerRequest request = routingContext.request();
    //      logger.info("{} {} {}", request.host(), request.method().name(), request.uri());
    //
    //      request
    //          .response()
    //          .setChunked(true)
    //          .putHeader("content-type", "text/plain")
    //          .end("hello from vertx");
    //    }
    //);
    //
    ///*
    // * you can call this with:
    // * curl localhost:8080/sensor/add -H "Content-Type: application/json" -X POST -d '{"x":123,"y":456,"z":789}'
    // */
    //router.route("/sensor/add").method(HttpMethod.POST).handler(routingContext -> {
    //      HttpServerRequest request = routingContext.request();
    //      logger.info("{}", routingContext.getBodyAsString());
    //
    //      request
    //          .response()
    //          .setChunked(true)
    //          .putHeader("content-type", "text/plain")
    //          .end("sensor reading received");
    //    }
    //);
    //
    //vertx
    //    .createHttpServer()
    //    .requestHandler(router::accept)
    //    .rxListen(8080)
    //    .subscribe(httpServer -> logger.info("server is running..."));

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
