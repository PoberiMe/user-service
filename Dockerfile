# Use Eclipse Temurin JDK 17 as base
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn/ .mvn/
COPY pom.xml .
COPY src ./src

# Make sure mvnw is executable
RUN chmod +x mvnw

# Build the app (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/user-service-0.0.1-SNAPSHOT.jar"]