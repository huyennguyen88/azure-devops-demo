
# Stage 1: Build application
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew

COPY src/ src/

RUN ./gradlew --no-daemon bootJar

# Stage 2: Run application
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
