FROM sathwik/apache-buildr:latest-jruby-jdk8 as builder

ENV JAVA_OPTS="-Xmx1024M -XX:MaxPermSize=512M"
ENV BUILDR_ARGS="-f Rakefile clean package test=no JAVADOC=off"

RUN apt-get update -qq && apt-get install -qqy \
        unzip \
    && rm -rf /var/lib/apt/lists/*

COPY . /workspace

RUN jruby -S bundler install --gemfile=/workspace/Gemfile \
    && buildr $BUILDR_ARGS \
    && mkdir /build \
    && cp /workspace/axis2-war/target/ode-axis2-war-1.3.8-SNAPSHOT.war /build/ode.war \
    && unzip /build/ode.war -d /build/ode


FROM tomcat:8.5-jre8
LABEL maintainer "Johannes Wettinger <jowettinger@gmail.com>, Michael Wurster <miwurster@gmail.com>, Michael Hahn <mhahn.dev@gmail.com>"

ARG DOCKERIZE_VERSION=v0.3.0

ENV TOMCAT_USERNAME admin
ENV TOMCAT_PASSWORD admin
ENV ENGINE_PLAN_PORT 8080
ENV LOG_LEVEL info

ENV TRADE_URL http://localhost:8081/api
ENV DYNAMIC_ODE_PORT 8080
ENV HOST_IP localhost

RUN rm /dev/random && ln -s /dev/urandom /dev/random \
    && wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY --from=builder /build/ode ${CATALINA_HOME}/webapps/ode

ADD tomcat-users.xml.tpl ${CATALINA_HOME}/conf/tomcat-users.xml.tpl
ADD manager.xml ${CATALINA_HOME}/conf/Catalina/localhost/manager.xml
ADD server.xml.tpl ${CATALINA_HOME}/conf/server.xml.tpl
ADD axis2.xml.tpl ${CATALINA_HOME}/webapps/ode/WEB-INF/conf/axis2.xml.tpl
ADD log4j2.xml.tpl ${CATALINA_HOME}/webapps/ode/WEB-INF/classes/log4j2.xml.tpl
ADD ode-axis2.properties.tpl ${CATALINA_HOME}/webapps/ode/WEB-INF/conf/ode-axis2.properties.tpl

EXPOSE 8080

CMD dockerize -template ${CATALINA_HOME}/conf/tomcat-users.xml.tpl:${CATALINA_HOME}/conf/tomcat-users.xml \
    -template ${CATALINA_HOME}/conf/server.xml.tpl:${CATALINA_HOME}/conf/server.xml \
    -template ${CATALINA_HOME}/webapps/ode/WEB-INF/conf/axis2.xml.tpl:${CATALINA_HOME}/webapps/ode/WEB-INF/conf/axis2.xml \
    -template ${CATALINA_HOME}/webapps/ode/WEB-INF/classes/log4j2.xml.tpl:${CATALINA_HOME}/webapps/ode/WEB-INF/classes/log4j2.xml \
    -template ${CATALINA_HOME}/webapps/ode/WEB-INF/conf/ode-axis2.properties.tpl:${CATALINA_HOME}/webapps/ode/WEB-INF/conf/ode-axis2.properties \
    ${CATALINA_HOME}/bin/catalina.sh run

#
# Manually build by running:
#
#   docker build -t trade4chor/trade-ode:local .
# 
# Run ODE container:
# 
#   docker run -d -p 8080:8080 --name ode trade4chor/trade-ode:local
# 
# Set a logging level different than "warn", e.g., for debugging process executions.
# Possible values are: all, trace, debug, info, warn, error, fatal, off.
# Visit Apache Log4j2 website for more details: https://logging.apache.org/log4j/2.x/.
# 
#   docker run -d -p 8080:8080 -e "LOG_LEVEL=debug" --name ode trade4chor/trade-ode:local
# 
