this small subproject creates the 'spark-without-hive' artifact

only usefull if the artifact have to be changed

preconditions:
 - expects spark to be checked out next to hive

usage:
 - choose the spark version you want to built
```
spark$ git checkout v2.0.0
```
 - build spark-without-hive artifact using:
```
spark$ sh ./dev/make-distribution.sh  --name hadoop2-without-hive --tgz -Phadoop-2.7 -Pyarn -Pparquet-provided -Dhadoop.version=2.7.3
```
 - edit the build.gradle file in this directory and update the `version` property to '2.0.0'
 - build the maven artifact
   - for local testing purposes
```
	./gradlew publishToMavenLocal
```
   - for remote deployment
```
	./gradlew publish '-Dpassword=myPassword'
```
     - after deploying to remote: don't forget to purge local maven caches about this artifact
```
$ rm -rf ~/.m2/repository/org/apache/hive/aux/
```
