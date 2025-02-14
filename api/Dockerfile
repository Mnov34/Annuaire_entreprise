FROM openjdk:21-jdk
LABEL authors="MNov34"
VOLUME /tmp
ARG JAR_FILE=target/api.jar
COPY ${JAR_FILE} api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api.jar"]
