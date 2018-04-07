#========== Basic Image ==========
FROM java:8-jre-alpine
MAINTAINER "DreamInSun"

#========== Environment ==========
ENV CONFIG_CONN       config.ecoho.cn
ENV SERVICE_NAME      cyan-svc-NazgulExample
ENV SERVICE_VERSION   LTS
ENV PROFILE           product
ENV API_VERSION       1.3.3

#========== Configuration ==========

#========== Install Application ==========
ADD target/*  /usr/local/
ADD config/*  /config/

#========== Expose Ports ==========
EXPOSE 8080


#========= Add Entry Point ==========
RUN apk add --no-cache --update-cache bash
ADD shell /shell
RUN chmod a+x /shell/*

#========= Volume Mount ==========
#VOLUME /data    /data

#========= Start Service ==========
#ENTRYPOINT ["/shell/docker-entrypoint.sh"]
CMD java -jar /usr/local/NazgulExample-1.3.3.jar docker /config/config.yml
