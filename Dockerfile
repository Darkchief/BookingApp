# Alpine linux with OpenJdk JRE
FROM adoptopenjdk/openjdk11

# Copy war file
COPY target/availability-api-1.0.0-SNAPSHOT.jar /availability.war

# Run the app
CMD ["java", "-jar", "availability.war"]