FROM gradle:jdk19 AS build
ARG JAR_FILE=build/libs/email-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
WORKDIR /app
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","-Xms512m", "-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF8", "-Duser.timezone=America/Sao_Paulo", "/app.jar"]