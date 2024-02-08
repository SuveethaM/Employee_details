FROM openjdk:17

WORKDIR /app

COPY target/springboot-mysql-docker.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
