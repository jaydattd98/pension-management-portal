FROM openjdk:8
EXPOSE 8080
ADD target/jwt-auth-docker.jar jwt-auth-docker.jar
ENTRYPOINT ["java", "-jar", "/jwt-auth-docker.jar"]