
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/*.jar app.jar
ENV JAVA_OPTS=""
ENV CONDUCTOR_SERVER_URL=http://conductor-server:8080/api/
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
