#!/bin/bash

(
cat << EOF
name: CI

on:
  pull_request:
    branches:
      - '*'
jobs:
EOF

M=100

for ((i=0;i<M;i++));do
cat << EOF
  split$i:
    name: 'split$i'
    runs-on: ubuntu-latest
    env:
      M_OPTS: -Dmaven.test.failure.ignore -Dtest.groups= -Pitests,qsplits -Dorg.slf4j.simpleLogger.log.org.apache.maven.plugin.surefire.SurefirePlugin=INFO
    steps:
      - uses: actions/checkout@v2
        with:
          fetch-depth: 50
      - name: 'Set up JDK 8'
        uses: actions/setup-java@v1
        with:
          java-version: 8
      - name: 'build'
        run: time mvn \$M_OPTS -B install -DskipTests
      - run: find . -name Test*.class | sed 's|.*org/apache/|org/apache/|'| sort -R --random-source=<(yes) | awk 'NR % $M == $i' > tests
      - run: wc -l tests
      - run: head tests
      - run: time mvn \$M_OPTS -q install -Dsurefire.includesFile=tests 

EOF
break
done
) > .github/workflows/main.yml