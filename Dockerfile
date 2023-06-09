#biblioteka uruchomieniowa
#jdk np eclipse temurin z docker hub
#domyslnie najnowsza wersja latest
#FROM eclipse-temurin
#ARG JAR_FILE=target/*.jar
#JAR_FILE jest teraz zmienna srodowiskową
#COPY ${JAR_FILE} app.jar
#port do komunikacji
#EXPOSE 8080
#polecenie uruchomieniowe
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM maven:3.6.3-openjdk-17-slim AS MAVEN_BUILD
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package


FROM openjdk:17-jdk-buster
EXPOSE 8080
COPY --from=MAVEN_BUILD /target/rental_server-0.0.1-SNAPSHOT.jar /app.jar

# Install maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["java","-jar","/app.jar"]

