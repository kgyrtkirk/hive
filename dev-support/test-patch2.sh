#!/bin/bash -e

[ "$1" == "" ] && echo "usage: $0 <patch_file>" && exit 1

container="`docker create yh`"
docker cp "$1" $container:/
docker exec -it $container -- /run_yetus.bash "`basename "$1"`"
r=$?
docker rm "$container"
exit $r
