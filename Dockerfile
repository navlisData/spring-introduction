# Maven image as the base image
FROM maven:3.9.9-amazoncorretto-17 AS build

# Set the working directory
WORKDIR /app

# Copy pom.xml and the project files
COPY pom.xml .
COPY app/src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests -e

# Use base image
FROM amazoncorretto:17

# Copy the built WAR file from the previous stage to the container
COPY app/target/app-0.0.1-SNAPSHOT.war app.war

# # Set the command to run the application
ENTRYPOINT ["java", "-jar", "./app.war"]