FROM openjdk:17-alpine

ENV GITHUB_TOKEN=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
ENV HOSTNAME=localhost

WORKDIR /app

COPY target/*.jar /app/app.jar
ADD ./src/main/resources/application.yml /app/application.yml

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]
