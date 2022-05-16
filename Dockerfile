FROM amazoncorretto:11-alpine
ARG JAR_FILE=target/oauth-resource-server-*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8084
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod","app.jar"]

