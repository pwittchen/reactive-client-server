reactive-client-server
======================
An example of reactive client and server apps written with Java 9, RxJava 2, Vert.x (on server) and Android (on client) for "Hack Your Career" presentation at Silesian University of Technology.

Project structure
-----------------
### server

to build and run the server, execute the following command:

```
cd server/ && ./server.sh
```

### client

to build client and install it on the connected device or emulator, execute the following command:

```
cd client/ && ./gradlew build installDebug
```

References
----------
- RxJava
  - https://github.com/ReactiveX/RxJava
  - https://github.com/ReactiveX/RxJava/wiki
  - http://reactivex.io/
  - http://rxmarbles.com/
  - https://www.reactivemanifesto.org/
- Vertx
  - http://vertx.io/
  - http://vertx.io/docs/vertx-core/java/
  - http://vertx.io/docs/vertx-rx/java2/
  - http://vertx.io/blog/some-rest-with-vert-x/
  - http://vertx.io/docs/vertx-web/java/
- Android
  - https://developer.android.com
  - https://github.com/ReactiveX/RxAndroid
  - http://square.github.io/retrofit/
