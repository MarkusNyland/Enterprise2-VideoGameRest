FROM openjdk:8-jdk-alpine
COPY target/VideoGameRest-exec.jar VideoGameRest-exec.jar 
CMD ["java","-jar","VideoGameRest-exec.jar"]

