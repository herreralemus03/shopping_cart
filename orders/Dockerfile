FROM openjdk:17-alpine

COPY target/*.jar /app.jar
ADD src/main/resources/bootstrap.yml /bootstrap.yml

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
