FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD springcloud-consul-blog-0.1.0.jar app.jar
RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""\
    SPRING_OUTPUT_ANSI_ENABLED=ALWAYS\
    SOS_SLEEP=0

EXPOSE 8083:8083
CMD echo "The ShengnianOS springcloud-sample will start in ${SOS_SLEEP}s" && \
    sleep ${SOS_SLEEP} && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
