#!/bin/bash -e

cd hive
git fetch
git checkout master
echo "*** preflight mvn install"
time mvn install -q -T4 -DskipTests -Pitests -DskipSparkTests
echo "*** run yetus"
test-patch --personality=/hive-personality.sh --skip-dir=dev-support "$@"
