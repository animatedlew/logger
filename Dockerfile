# A Logger Seed for Scala Apps
# Lewis M.

# build image:
# >> docker build -t cota/helix .
# inspect log:
# >> docker run -it --rm cota/helix cat /var/log/cota/helix/debug.log

FROM  hseeberger/scala-sbt:latest

ENV SBT_OPTS="-Xms1G -Xmx4G -Xss1M"

# comment this line out to test default logback settings
ENV LOGBACK_FILE="-Dlogback.configurationFile=/opt/cota/helix/logback.xml"

RUN mkdir -p /var/log/cota/helix /root/helix /opt/cota/helix
RUN touch /var/log/cota/helix/debug.log

# move logback conf to a global location
COPY src/main/resources/logback.xml /opt/cota/helix

WORKDIR /root/helix

# TODO: pull latest project from github instead of copying from current directory
COPY . /root/helix
RUN sbt assembly

# pass in logback ref to jvm runtime
RUN java $SBT_OPTS $LOGBACK_FILE -jar target/scala-2.12/log.jar
RUN cat /var/log/cota/helix/debug.log
