# Using OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper files
COPY gradle/ gradle/
COPY gradlew gradlew.bat ./
COPY build.gradle settings.gradle ./

# Give execution permissions to the gradle wrapper
RUN chmod +x ./gradlew

# Copy the source code
COPY src/ src/

# Build the application
RUN ./gradlew build -x test

# Create directory for the JAR
RUN mkdir -p /app/libs

# Copy the built JAR
RUN cp build/libs/*.jar /app/app.jar 2>/dev/null || echo "No JAR found, will use class files"

# Expose the port
EXPOSE 8080

# Command to run the application directly with Java
CMD ["java", "-cp", "build/classes/java/main", "Main"]