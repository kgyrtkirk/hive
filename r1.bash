#!/bin/bash -e

export MAVEN_OPTS="-Xmx2g"
export M_OPTS="-Dmaven.test.failure.ignore -Dtest.groups= -Pitests,qsplits -Dorg.slf4j.simpleLogger.log.org.apache.maven.plugin.surefire.SurefirePlugin=INFO"

set -x

case "$1" in
	build)
		echo "@ build"
		mvn $M_OPTS -B install -DskipTests
	;;
	test)
		M=$3
		i=$2
		echo "@ tests $i / $M"
		find . -name Test*.class |
			sed 's|.*org/apache/|org/apache/|'|
			sort -R --random-source=<(yes) |
			awk "NR % $M == $i" > tests
		wc -l tests
		head tests
		mvn $M_OPTS -q install -Dsurefire.includesFile=$PWD/tests
	;;
	*)
		echo "unknown $?"
		exit 1
	;;
esac

