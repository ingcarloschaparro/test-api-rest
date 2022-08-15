# https://spring.io/guides/gs/spring-boot-docker/
FROM eclipse-temurin:11-alpine
MAINTAINER Modusoftware

RUN apk update \
    && apk add busybox-extras \
    && apk add nano \ 
    && apk add vim \
    && apk --no-cache add curl \
    && apk add tzdata \
    && cp /usr/share/zoneinfo/America/Bogota /etc/localtime \
    && echo 'America/Bogota' > /etc/timezone 
#    && mkdir /logs \
#    && mkdir /config

#VOLUME /config
#VOLUME /logs

ENV NAME_LOG_FILE=$PROJECT_NAME
ARG JAR_NAME

COPY target/${JAR_NAME} /app.jar

EXPOSE 80

ENTRYPOINT [ "sh", "-c", "java -jar -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -Dserver.port=80 -Xmx256m -Xms128m /app.jar" ]

##Build
# docker build -t ingcarloschaparro/test-api-rest --build-arg JAR_NAME=test-api-rest-1.0.0.jar .
##Run
#  docker run -d --name test-api-rest -e PROFILE=dev -e NAME_LOG_FILE=test-api-rest -e WS_PORT=8989 -v "/home/saenz-laptop/git/Skillnet/skillnet-demo-ws/src/skillnet-demo-ws/logsDocker:/logs" -v "/home/saenz-laptop/git/Skillnet/skillnet-demo-ws/src/skillnet-demo-ws/config:/config" -p 8989:8989 ingcarloschaparro/test-api-rest
#  docker run -d --name test-api-rest -e PROFILE=dev -e NAME_LOG_FILE=test-api-rest -e WS_PORT=8989 -p 8989:80 ingcarloschaparro/test-api-rest
