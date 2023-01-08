FROM node:18-alpine as frontend

COPY frontend/ /build/

WORKDIR /build/

RUN npm install && npm run build

FROM gradle:jdk19-alpine AS backend

COPY backend/ /build/

COPY --from=frontend /build/dist/ /build/src/main/resources/static/

WORKDIR /build/

RUN gradle bootJar --no-daemon

FROM openjdk:19-jdk-slim

WORKDIR /

COPY --from=backend /build/build/libs/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
