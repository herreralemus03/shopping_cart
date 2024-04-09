FROM openjdk:17-alpine

COPY target/*.jar /app.jar
ADD src/main/resources/application-dev.yml /application-dev.yml

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]
