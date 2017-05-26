#!/bin/bash -e

[ "$1" == "" ] && echo "usage: $0 <patch_file>" && exit 1

#container="`docker create yh`"
#docker cp "$1" $container:/
PF="$1"
[ "${PF:0:1}" == "." ] && PF="`pwd`/$PF"
PN="`basename "$PF"`"
echo $PN

#docker run --rm -it -v ~/.m2:/root/.m2 -v "$PF:/$PN" yh /run_yetus.bash "/$PN"
docker run --rm -it -v ~/.m2/settings.xml:/root/.m2/settings.xml -v "$PF:/$PN" yh /run_yetus.bash "/$PN"
