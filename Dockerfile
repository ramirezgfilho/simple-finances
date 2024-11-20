# Etapa 1: Construção do projeto
FROM openjdk:23-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# Etapa 2: Imagem final para executar o aplicativo
FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
