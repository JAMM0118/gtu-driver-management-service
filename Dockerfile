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


COPY --from=build /app/target/*.jar app.jar


EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
