#!/bin/bash -ex

[ "$1" == "" ] && echo "usage: $0 <patch_file|patch_url> [more yetus args]" && exit 1

PF="$1"
shift

#[ "${PF:0:1}" == "." ] && PF="`pwd`/$PF"
PN="`basename "$PF"`"
echo $PN

# this part will launch an nginx; the tested patch will be loaded into it
nginx_name='hive_yetus_nginx'
if [ "$(docker ps -q -f "name=^/$nginx_name$")" == "" ]; then
	echo "* launch $nginx_name"
	docker run --restart=always --name $nginx_name -d nginx
fi

# the yetus test container expects an URL, so if its a local file; will load it into the nginx
if [ "${PF//http*/}" == "" ];then
	PATCH_URL="$PF"
else
	docker cp "$PF" $nginx_name:"/usr/share/nginx/html/$PN"
	PATCH_URL="http://$nginx_name/$PN"
fi

docker run --rm -it 		\
	--link hive_yetus_nginx	\
	-v ~/.m2/settings.xml:/root/.m2/settings.xml \
	kgyrtkirk/hive_yetus \
	/run_yetus.bash "$PATCH_URL" "$@"
