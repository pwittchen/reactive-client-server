#!/usr/bin/env bash
# compile and start the server
./gradlew shadowJar && java -jar build/libs/server-fat.jar