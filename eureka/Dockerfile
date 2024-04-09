FROM openjdk:17-alpine

ENV HOSTNAME=localhost

WORKDIR /app

COPY target/*.jar /app/app.jar
COPY ./src/main/resources/ /app/

EXPOSE 8761

CMD ["java", "-jar", "app.jar"]
