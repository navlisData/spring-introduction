# Stage 1: Build the application using Maven
FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /app

# Copy pom.xml and the project files
COPY pom.xml .
COPY app/pom.xml app/
COPY app/src app/src

# Build the application using Maven
RUN mvn clean package -DskipTests -e

# Stage 2: Create the final Docker image with only the necessary artifacts
FROM amazoncorretto:17

# Copy the built WAR file from the build stage to the container
COPY --from=build /app/app/target/app-0.0.1-SNAPSHOT.war ./app.war

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "./app.war"]