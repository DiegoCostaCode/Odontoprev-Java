FROM gradle:8.10.1-jdk21-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN  gradle build --no-daemon

FROM openjdk:21-jdk-slim

EXPOSE 8080

RUN  mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/odontoprev-java-api.jar

ENTRYPOINT ["java", "-jar", "/app/odontoprev-java-api.jar"]