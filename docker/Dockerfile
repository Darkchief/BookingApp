# Alpine linux with OpenJdk JRE
FROM openjdk:11-jre-alpine

# Copy war file
COPY availability-api-1.0.0-SNAPSHOT.jar /availability.war

# Run the app
CMD ["/usr/bin/java", "-jar", "availability.war"]