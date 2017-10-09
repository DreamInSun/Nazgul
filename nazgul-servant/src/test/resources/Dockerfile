#========== Basic Image ==========
FROM java:8-jre-alpine
MAINTAINER "DreamInSun"

#========== Environment ==========
ENV CONFIG_CONN       config.ecoho.cn
ENV SERVICE_NAME      cyan.nazgul.example
ENV SERVICE_VERSION   LTS
ENV PROFILE           product
ENV API_VERSION       1.0.0
ENV CONFIG_KEY        123456
#orangelife 安全性，product配置应在运行时配置，或者将PROFILE改为不需密码版本


#========== Configuration ==========
#ENV _JAVA_OPTIONS: "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8080 -Xms128m -Xmx256M"
#ENV CLASSPATH: /app/lib/*:/app/classes

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
