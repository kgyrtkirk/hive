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

M=2

for ((i=0;i<M;i++));do
cat << EOF
  test$i:
    name: 'test$i'
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
        with:
          fetch-depth: 50
      - name: 'Set up JDK 8'
        uses: actions/setup-java@v1
        with:
          java-version: 8
      - name: 'build'
        run: time ./r1 build
      - name: 'test $i/$M'
        run: time ./r1 test $i $M

EOF
#break
done
) > .github/workflows/main.yml