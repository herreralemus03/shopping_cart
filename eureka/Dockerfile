FROM openjdk:17-alpine

COPY target/*.jar /app.jar
ADD src/main/resources/bootstrap-dev.yml /bootstrap-dev.yml

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]

