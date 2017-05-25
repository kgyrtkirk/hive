FROM debian:stretch

RUN apt-get update && apt-get install -y git diffutils openjdk-8-jdk-headless maven && apt-get clean
RUN apt-get install -y wget && apt-get clean

RUN git clone --no-checkout https://github.com/apache/hive

COPY install_yetus.bash /
RUN /install_yetus.bash
ENV PATH=$PATH:/yetus/bin

COPY hive-personality.sh /
COPY run_yetus.bash /

CMD /bin/bash
#test-patch --personality=/hive-personality.sh --skip-dir=dev-support "$@"

