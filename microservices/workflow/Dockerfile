# Base image for running Java applications
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /home

# Copy the JAR file from your build context into the container
# Ensure that the 'target' directory is in the same location as your Dockerfile
COPY workflow-rest/target/workflow-rest-1.0.jar workflow-1.0.jar

# Copy the entrypoint script
COPY docker-entrypoint.sh docker-entrypoint.sh

# Make the entrypoint script executable
RUN chmod +x docker-entrypoint.sh

EXPOSE 8083

# Define the entrypoint script
# This will be executed when the container starts
ENTRYPOINT ["/home/docker-entrypoint.sh"]

# Define the default command to be passed to the entrypoint script
# This is typically the command to run your Java application
CMD ["workflow-1.0.jar"]