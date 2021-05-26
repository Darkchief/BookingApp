# Alpine linux with OpenJdk JRE
FROM adoptopenjdk/openjdk11

# Copy war file
COPY target/availability-api-1.1.1-RELEASE.jar /availability.war

# Run the app
CMD ["java", "-jar", "availability.war"]
