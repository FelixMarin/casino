# Use official maven/Java 17 image as the base image
FROM maven:3.8.4-openjdk-17 as build

# Set the current working directory inside the docker image
WORKDIR /app

# Copy pom.xml and src directory to the image
COPY pom.xml .
COPY src ./src

# Build the project inside the docker image
RUN mvn clean package

# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Add Maintainer Info
LABEL maintainer="FelixMarin"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set the application's jar file
ARG JAR_FILE=target/*.jar

# Copy the application's jar file to the filesystem of the image
COPY --from=build /app/${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]