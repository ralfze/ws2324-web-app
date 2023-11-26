# Stage 1: Build
FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY backend/pom.xml .
RUN mvn dependency:go-offline
COPY backend/src ./src
RUN mvn package -DskipTests

# Stage 2: Production-ready image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY backend/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
EXPOSE 8080
CMD ["java","-jar", "-javaagent:opentelemetry-javaagent.jar" ,"app.jar"]]