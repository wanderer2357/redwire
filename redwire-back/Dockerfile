# MULTI-STAGE build
# STAGE 1 : build the app
FROM maven:3.9.9-amazoncorretto-23-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# STAGE 2 : run the app
FROM amazoncorretto:23-alpine3.20-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar redwire.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "redwire.jar"]