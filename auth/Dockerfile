FROM openjdk:17-alpine

WORKDIR /app

COPY target/*.jar /app/app.jar
COPY ./src/main/resources/ /app/

EXPOSE 9000

CMD ["java", "-Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/app/bootstrap.yml", "app.jar"]
