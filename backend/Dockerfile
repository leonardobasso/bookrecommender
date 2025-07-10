FROM eclipse-temurin:21-jdk-alpine
COPY target/app.jar /app.jar
# This is the port that your javalin application will listen on
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "/app.jar"]
