#!/bin/bash -e

PATCH_URL="$1"
PATCH_FILE="/`basename "$PATCH_URL"`"
shift

echo "*** update hive repo"
cd hive
git clean -dfx
git checkout master
git pull
echo "*** fetching patch($PATCH_URL)"
wget -O "$PATCH_FILE" "$PATCH_URL"
echo "*** preflight mvn install"
time mvn install -q -T4 -DskipTests -Pitests -DskipSparkTests
echo "*** run yetus"
test-patch --personality=/hive-personality.sh --skip-dir=dev-support "$PATCH_URL" "$@"
