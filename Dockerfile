# Stage 1: Build the application
# Use a JDK image that includes a build tool like Maven or Gradle.
# This stage compiles the source code and creates the executable JAR.
FROM eclipse-temurin:21-jdk-jammy AS builder


# Copy the build files first to leverage Docker's caching.
# If these files don't change, Docker can reuse the layer.
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
# If you are using Maven:
# COPY pom.xml .

# Copy the rest of the source code
COPY src src

# Run the build command
# For Gradle:
RUN ./gradlew clean build --no-daemon

# For Maven:
# RUN ./mvnw clean package

# Stage 2: Create the final, lean image for running the application
# Use a JRE image that is smaller than the full JDK image.
# This image only needs to run the pre-built JAR.
FROM eclipse-temurin:21-jre-jammy AS final

# Set the working directory
WORKDIR /src

# Copy the built JAR from the 'builder' stage
# The path is relative to the build directory created in the first stage.
COPY --from=builder /build/libs/*-all.jar app.jar
# Adjust 'your-application-name' and the path based on your build tool's output.

# Expose the port your Micronaut application listens on
# The default port for Micronaut is 8080.
EXPOSE 8080

# Define the command to run the application when the container starts
# The '-jar' flag is used to execute the fat JAR.
ENTRYPOINT ["java", "-jar", "app.jar"]

# You can also add more configurations like environment variables for your application.
# ENV BROWSER=chrome