# First stage: Build the application
FROM maven:3.8.5-eclipse-temurin-17 AS build

WORKDIR /Health-dkg
COPY . .
RUN mvn package -Dmaven.test.skip=true

# Second stage: Run the application
FROM openjdk:18-jdk

WORKDIR /app
COPY --from=build /Health-dkg/target/health-dkg.jar /app/health-dkg.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "health-dkg.jar"]
