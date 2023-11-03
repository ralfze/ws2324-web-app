FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
ARG JAR_FILE
COPY target/diceapp-0.0.1-SNAPSHOT.jar app.jar
COPY opentelemetry-javaagent.jar opentelemetry-javaagent.jar
ENTRYPOINT ["java","-jar", "-javaagent:opentelemetry-javaagent.jar" ,"app.jar"]