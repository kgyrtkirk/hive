#!/bin/bash

cd hive
git fetch
git checkout master
test-patch --personality=/hive-personality.sh --skip-dir=dev-support "$@"
