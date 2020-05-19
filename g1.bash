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
      - name: 'test $i/$M'
        run: time ./r1 test $i $M

EOF
done

cat << EOF
  deployResults:
    runs-on: ubuntu-latest
    steps:
      - run: echo asd > README.md
      - name: Deploy 🚀
        uses: JamesIves/github-pages-deploy-action@releases/v3
        with:
          ACCESS_TOKEN: \${{ secrets.ACCESS_TOKEN }}
          BRANCH: gh-pages # The branch the action should deploy to.
          FOLDER: build # The folder the action should deploy.

EOF


) > .github/workflows/main.yml