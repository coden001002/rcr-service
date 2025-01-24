# Download maven for Jdk17
FROM maven:3.6.3-openjdk-17-slim AS build

# Copy code and pom
COPY src /home/app/src
COPY pom.xml /home/app

# Expose port 80
EXPOSE 80

# Run
RUN mvn -f /home/app/pom.xml spring-boot:run
