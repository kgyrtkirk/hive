#!/bin/bash -ex
set -o pipefail

YETUS_VERSION=0.4.0

wget -O /tmp/yetus.tar.gz https://archive.apache.org/dist/yetus/${YETUS_VERSION}/yetus-${YETUS_VERSION}-bin.tar.gz
tar xzf /tmp/yetus.tar.gz
rm /tmp/yetus.tar.gz

#ensure exists&link
[ -d yetus-$YETUS_VERSION ]
ln -s yetus-$YETUS_VERSION yetus

function apply() {
	echo "applying $1"
	wget -O- "$1" | patch -p1
}

cd yetus/lib
#apply some project specific fixes
apply https://issues.apache.org/jira/secure/attachment/12843859/YETUS-471.03.patch
apply https://issues.apache.org/jira/secure/attachment/12849981/YETUS-484.00.patch
apply https://issues.apache.org/jira/secure/attachment/12868496/YETUS-506.03.patch

