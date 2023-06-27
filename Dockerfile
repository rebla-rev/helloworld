# Dockerfile for deploying a simple Java Spring Boot application
# to a Docker container. This Dockerfile is used for the
# "Deploying a Java Spring Boot application to a Docker container"
# tutorial.

FROM openjdk:17-jdk-alpine

COPY target/helloworld-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
