#!/bin/bash -ev

SPARK_VERSION="${1:-2.0.0}"

ARTIFACT="spark-${SPARK_VERSION}-bin-hadoop2-without-hive.tgz"

REPO="$PWD/repo"
PKG_DEST="$REPO/org/apache/hive/aux/spark-without-hive/${SPARK_VERSION}/"
PKG_NAME="spark-without-hive-${SPARK_VERSION}"

mkdir -p tmp "$PKG_DEST"

cd tmp
tar xzf "../$ARTIFACT"
cd "spark-${SPARK_VERSION}-bin-hadoop2-without-hive"
jar cf "$PKG_DEST/${PKG_NAME}-assembly.jar" .


cat > "$PKG_DEST/$PKG_NAME.pom" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>org.apache.hive.aux</groupId>
  <artifactId>spark-without-hive</artifactId>
  <version>${SPARK_VERSION}</version>
</project>
EOF
