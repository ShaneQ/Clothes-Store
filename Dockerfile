FROM amazoncorretto:11-alpine
ARG JAR_FILE=target/oauth-resource-server-*.jar
ARG PROPERTIES_FILE=src/main/resources/application-prod.yml

COPY ${JAR_FILE} app.jar
COPY ${PROPERTIES_FILE} application.yml

EXPOSE 8084
ENTRYPOINT ["java","-jar","app.jar"]

