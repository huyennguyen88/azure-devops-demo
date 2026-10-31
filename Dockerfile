FROM eclipse-temurin:21-jre
WORKDIR /app
ARG GIT_COMMIT=local
ENV APP_VERSION=${GIT_COMMIT}
COPY build/libs/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
