#========== Basic Image ==========
FROM java:8-jre-alpine
MAINTAINER "DreamInSun"

#========== Environment ==========
ENV CONFIG_CONN       config.ecoho.cn
ENV SERVICE_NAME      cyan.svc.NazgulExample
ENV SERVICE_VERSION   LTS
ENV PROFILE           product
ENV API_VERSION       1.2.1

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

#========= Start Service ==========
#ENTRYPOINT ["/shell/docker-entrypoint.sh"]
CMD java -jar /usr/local/cyan.core.ucenter.jar docker /config/config.yml
