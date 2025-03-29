# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built application JAR
COPY target/expediaoffers-0.0.1-SNAPSHOT.jar app.jar

# Expose port (same as in `application.properties`)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
