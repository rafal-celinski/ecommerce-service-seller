FROM eclipse-temurin:17 AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
RUN apt-get update && apt-get install -y wait-for-it
RUN echo "spring.datasource.url=jdbc:postgresql://postgres:5432/${DB_NAME}" > application.properties && \
    echo "spring.datasource.username=${DB_USER}" >> application.properties && \
    echo "spring.datasource.password=${DB_PASSWORD}" >> application.properties && \
    echo "spring.jpa.hibernate.ddl-auto=update" >> application.properties && \
    echo "upload.dir=/app/uploads" >> application.properties
    
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-Dspring.config.location=file:application.properties", "-jar", "app.jar"]