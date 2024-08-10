FROM eclipse-temurin:21-jdk-alpine
MAINTAINER jopimi.dev@gmail.com
COPY target/beach-flag-0.0.1-SNAPSHOT.jar beach-flag-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/beach-flag-0.0.1-SNAPSHOT.jar"]
