# Etapa 1: build
FROM ubuntu:latest AS builder

RUN apt-get update && apt-get install -y openjdk-17-jdk maven
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Etapa 2: execução
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
EXPOSE 8080

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
