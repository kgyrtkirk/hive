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

M=20

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
      - name: Unit tests results
        uses: actions/upload-artifact@v1
        with:
          name: unit-tests-results
          path: storage-api/target/site/surefire-report.html
EOF
done


# cat << EOF
#   deployResults:
#     runs-on: ubuntu-latest
#     steps:
#       - uses: actions/checkout@v2
#         with:
#           fetch-depth: 50
#           repository: 'kgyrtkirk/test-results'
#           ref: 'gh-pages'
#       - run: echo asd > README.md
#       - name: Commit report
#         run: |
#           git config --global user.name 'Your Name'
#           git config --global user.email 'your-username@users.noreply.github.com'
#           git remote set-url origin https://x-access-token:${{ secrets.GITHUB_TOKEN }}@github.com/${{ github.repository }}
#           git commit -am "Automated report"
#           git push

# EOF


) > .github/workflows/main.yml