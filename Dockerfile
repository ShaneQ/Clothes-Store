FROM adoptopenjdk/openjdk11-openj9
ARG JAR_FILE=target/oauth-resource-server-*.jar
ARG PROPERTIES_FILE=src/main/resources/application-prod.yml

COPY ${JAR_FILE} app.jar
ADD ${PROPERTIES_FILE} /application.yml

ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom -Dspring.config.location=classpath:file:/application.yml","-jar","app.jar"]
EXPOSE 8084

