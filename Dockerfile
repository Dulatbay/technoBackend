# Stage 1: Build Stage
FROM gradle:8.0-jdk17 as build
WORKDIR /app
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src ./src
RUN gradle clean build

# Stage 2: Run Stage
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=build /app/build/libs/technoBackend-0.0.1-SNAPSHOT.jar ./demo-aws.jar
EXPOSE 8080
CMD ["java", "-jar", "demo-aws.jar"]
