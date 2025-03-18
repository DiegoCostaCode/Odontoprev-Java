FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src
COPY gradlew ./

RUN chmod +x gradlew

RUN ./gradlew build --no-daemon

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]