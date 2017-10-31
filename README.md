reactive-client-server
======================
An example of reactive client and server apps for "Hack Your Career" presentation at Silesian University of Technology.

Project structure
-----------------
### server

uses Java 8, Gradle, RxJava 2, Vert.x (Core and Rx), Logback

to build and run the server, execute the following command:

```
cd server/ && ./server.sh
```

### client

uses Java 7, Gradle, Android, RxJava 2, OkHttp 3, ButterKnife, Retrolambda, ReactiveSensors

to build client and install it on the connected Android device or emulator, execute the following command:

```
cd client/ && ./gradlew build installDebug
```

Code style
----------

- Code style used by server is called `Square`
- Code style used by client is called `SquareAndroid`
- Java Code Styles repository by Square is available at: https://github.com/square/java-code-styles

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
