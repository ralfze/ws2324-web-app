FROM eclipse-temurin:17-jdk-jammy
#VOLUME /tmp
# Set the working directory in the container
WORKDIR /app/
ARG JAR_FILE
COPY backend/target/diceapp-0.0.1-SNAPSHOT.jar app.jar
COPY backend/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
ENTRYPOINT ["java","-jar", "-javaagent:opentelemetry-javaagent.jar" ,"app.jar"]