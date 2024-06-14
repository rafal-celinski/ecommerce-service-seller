FROM eclipse-temurin:17 AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17
WORKDIR /app    
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]