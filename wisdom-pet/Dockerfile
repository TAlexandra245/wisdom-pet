FROM openjdk:17-jdk as BUILD_IMAGE
ADD build/libs/wisdom-pet-0.0.1-SNAPSHOT.jar wisdom-pet-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "wisdom-pet-0.0.1-SNAPSHOT.jar"]

