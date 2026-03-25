# Stage 1: Build the Spring Boot application using Maven
FROM eclipse-temurin:17-jdk-focal AS builder

# Set working directory
WORKDIR /app

# Copy Maven wrapper (recommended) and config files
COPY mvnw .
COPY .mvn .mvn

# Copy pom.xml
COPY pom.xml .

# Download dependencies first (better caching)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
# -DskipTests skips tests for faster build
RUN ./mvnw clean package -DskipTests

# Stage 2: Create lightweight runtime image
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copy JAR from builder stage
# Adjust the JAR name if different
COPY --from=builder /app/target/*.jar redisDemo.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "redisDemo.jar"]