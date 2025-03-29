# Use an official OpenJDK runtime as a base image
FROM maven:3.8.4-openjdk-11 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and dependencies first for caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the application
COPY . .

# Build the application
RUN mvn clean package

# Use a lightweight JDK image
FROM openjdk:11-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/expediaoffers-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
