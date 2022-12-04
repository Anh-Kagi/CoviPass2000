FROM gradle:jdk19-alpine AS builder

WORKDIR /

COPY backend/ /

RUN gradle bootJar --no-daemon

FROM openjdk:19-jdk-slim

WORKDIR /

COPY --from=builder /build/libs/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
