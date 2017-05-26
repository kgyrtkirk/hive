#!/bin/bash -e

cd hive
git clean -dfx
git checkout master
git pull
echo "*** preflight mvn install"
time mvn install -q -T4 -DskipTests -Pitests -DskipSparkTests
echo "*** run yetus"
test-patch --personality=/hive-personality.sh --skip-dir=dev-support "$@"
