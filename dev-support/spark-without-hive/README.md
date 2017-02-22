this small subproject creates the 'spark-without-hive' artifact

only usefull if the artifact have to be changed

usage:
 - choose the spark version you want to built
```
spark$ git checkout v2.0.0
```
 - build spark-without-hive artifact using:
```
spark$ sh ./dev/make-distribution.sh  --name hadoop2-without-hive --tgz -Phadoop-2.7 -Pyarn -Pparquet-provided -Dhadoop.version=2.7.3
```
 - move the resulting spark-2.0.0-bin-hadoop2-without-hive.tgz to this directory
 - run ./repack.sh
 - upload/publish 'repo' directory

note: during testing, don't forget to purge local maven caches about this artifact
```
$ rm -rf ~/.m2/repository/org/apache/hive/aux/
```
