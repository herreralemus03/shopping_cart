FROM openjdk:17-alpine

WORKDIR /app

COPY target/*.jar /app/app.jar
COPY ./src/main/resources/ /app/

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
