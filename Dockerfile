# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/rcr-service-1.0-SNAPSHOT-jar-with-dependencies.jar /app/rcr-service.jar

# Make port 80 available to the world outside this container
EXPOSE 80

# Run the application
ENTRYPOINT ["java", "-jar", "rcr-service.jar"]
