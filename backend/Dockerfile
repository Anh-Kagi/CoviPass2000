FROM gradle:jdk19-alpine AS builder

COPY . .

RUN gradle bootJar --no-daemon

FROM openjdk:19-jdk-slim

WORKDIR /

COPY --from=builder /home/gradle/build/libs/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
