# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim AS build

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and source files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Build the project inside the container
RUN ./mvnw clean package -DskipTests

# Stage 2: Use a lightweight OpenJDK 17 base image for running the application
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/urltrim.jar app.jar

# Expose the app port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
