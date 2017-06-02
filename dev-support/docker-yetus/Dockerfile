FROM debian:stretch
MAINTAINER Zoltan Haindrich <kirk@rxd.hu>

RUN apt-get update && apt-get install -y curl wget git diffutils openjdk-8-jdk-headless maven findbugs && apt-get clean

RUN git clone --no-checkout https://github.com/apache/hive
#COPY settings.xml /root/.m2/
# warm up image-internal .m2 cache
RUN git clone hive hive-ws && cd hive-ws && mvn install -q -T4 -DskipTests -Pitests -DskipSparkTests && cd / && rm -rf hive-ws /root/.m2/settings.xml /root/.m2/repository/org/apache/hive

COPY install_yetus.bash /
RUN /install_yetus.bash
ENV PATH=$PATH:/yetus/bin
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
COPY hive-personality.sh /
COPY run_yetus.bash /

CMD /bin/bash
ENV FINDBUGS_HOME=/usr/
#test-patch --personality=/hive-personality.sh --skip-dir=dev-support "$@"

