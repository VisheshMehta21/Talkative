# Use a base image with JDK
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the target folder to the container
COPY target/talkative-0.0.1-SNAPSHOT.jar Backend/talkative/talkative-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8000

# Run the jar file
ENTRYPOINT ["java", "-jar", "Backend/talkative/talkative-0.0.1-SNAPSHOT.jar"]
