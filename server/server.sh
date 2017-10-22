#!/usr/bin/env bash

echo "======> starting compilation..."
echo ""

./gradlew shadowJar

echo "======> compilation finished"
echo ""
echo "======> running server..."

echo "
__   _____ _ __| |___  __
\ \ / / _ \ '__| __\ \/ /
 \ V /  __/ |  | |_ >  <
  \_/ \___|_|   \__/_/\_\

"

java -jar build/libs/server-fat.jar