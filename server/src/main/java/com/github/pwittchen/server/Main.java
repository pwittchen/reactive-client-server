package com.github.pwittchen.server;

import io.reactivex.functions.Consumer;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerRequest;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO #1 apply RxJava2
//TODO #2 create REST server
//TODO #3 log sensor events

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String args[]) {
    //HttpServer server = Vertx.vertx().createHttpServer();

    Vertx vertx = Vertx.vertx();
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    router.route("/").method(HttpMethod.GET).handler(routingContext -> {
          HttpServerRequest request = routingContext.request();
          logger.info("{} {} {}", request.host(), request.method().name(), request.uri());

          request
              .response()
              .setChunked(true)
              .putHeader("content-type", "text/plain")
              .end("hello from vertx");
        }
    );

    router.route("/sensor/add").method(HttpMethod.POST).handler(routingContext -> {
          HttpServerRequest request = routingContext.request();
          logger.info("{}", routingContext.getBodyAsString());

          request
              .response()
              .setChunked(true)
              .putHeader("content-type", "text/plain")
              .end("sensor reading received");
        }
    );

    HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestHandler(router::accept).listen(8080);

    //server
    //    .requestStream()
    //    .toObservable()
    //    .subscribe(request -> {
    //      HttpServerResponse response = request.response();
    //      response.setChunked(true);
    //      logger.info("{} {} {}", request.host(), request.method().name(), request.uri());
    //      request
    //          .toObservable()
    //          .subscribe(buffer ->
    //              response
    //                  .putHeader("content-type", "text/html")
    //                  .end("hello from Vertx"));
    //    });

    //server.listen(8080);
  }
}
