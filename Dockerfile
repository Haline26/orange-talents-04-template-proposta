## Builder Image
FROM maven:3.6.3-jdk-11 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

## Runner Image
FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY --from=builder /usr/src/app/${JAR_FILE} /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]