FROM amazoncorretto:11-alpine
ARG JAR_FILE=target/oauth-resource-server-*.jar
COPY ${JAR_FILE} app.jar
ADD ${PROPERTIES_FILE} /application.yml
ENTRYPOINT exec java $JAVA_OPTS  -jar --spring.config.location=classpath:file:/application.yml /app.jar

EXPOSE 8084

