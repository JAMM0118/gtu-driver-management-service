# Etapa 1: Compila el JAR
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests && mv target/*.jar target/app.jar

# Etapa 2: Imagen final con netcat
# Etapa 2: Imagen final

FROM eclipse-temurin:17-jdk

WORKDIR /app

# Instalar netcat y curl para los scripts de espera
RUN apt-get update && \
    apt-get install -y netcat-openbsd && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN apt-get update && apt-get install -y curl iputils-ping

COPY --from=build /app/target/app.jar app.jar
COPY wait-for-services.sh wait-for-services.sh

RUN chmod +x wait-for-services.sh

EXPOSE 8081

ENTRYPOINT ["./wait-for-services.sh", "postgres-main", "5432", "http://discovery-server:8761/eureka/apps"]
