#
# Build stage
#
#TODO I couldn't run it on mac with error "docker pull maven:3.8.7-eclipse-temurin-17-alpine
#
#                                    3.8.7-eclipse-temurin-17-alpine: Pulling from library/maven
#                                          no matching manifest for linux/arm64/v8 in the manifest list entries"
#Try to use universal images, eg maven:3.9.6-eclipse-temurin-17, yours is compartible with linux only
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:17-slim-buster
COPY --from=build /home/app/target/k-curry-jib-customer-0.0.1-SNAPSHOT.jar /usr/local/lib/k-curry-jib-customer.jar
EXPOSE 8889
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-jar","/usr/local/lib/k-curry-jib-customer.jar"]
