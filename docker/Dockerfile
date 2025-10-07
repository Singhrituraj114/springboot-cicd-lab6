# =============================================================================
# Multi-Stage Dockerfile for Spring Boot Application
# Optimized for size and security
# =============================================================================

# -----------------------------------------------------------------------------
# Stage 1: Build Stage
# Use Maven image to build the application
# -----------------------------------------------------------------------------
FROM maven:3.9.5-eclipse-temurin-17-alpine AS builder

# Set working directory
WORKDIR /app

# Copy Maven configuration files first (for better layer caching)
COPY pom.xml .
COPY src ./src

# Build the application
# Skip tests during Docker build (tests run in Jenkins pipeline)
RUN mvn clean package -DskipTests

# -----------------------------------------------------------------------------
# Stage 2: Runtime Stage
# Use minimal JRE image for running the application
# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre-alpine

# Set metadata labels
LABEL maintainer="your-email@example.com"
LABEL application="spring-boot-cicd"
LABEL description="Spring Boot application with CI/CD pipeline"

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring

# Set working directory
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Change ownership to non-root user
RUN chown -R spring:spring /app

# Switch to non-root user
USER spring

# Expose application port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Set JVM options for container environment
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# =============================================================================
# Build Instructions:
# 
# 1. Build the image:
#    docker build -t your-dockerhub-username/springboot-app:latest .
#
# 2. Run the container:
#    docker run -p 8080:8080 your-dockerhub-username/springboot-app:latest
#
# 3. Push to Docker Hub:
#    docker login
#    docker push your-dockerhub-username/springboot-app:latest
#
# =============================================================================
