FROM eclipse-temurin:21-jdk-alpine
COPY target/app.jar /app.jar
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "/app.jar"]