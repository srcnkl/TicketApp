FROM openjdk:8
EXPOSE 8080
ADD target/ticket-app-docker.jar  ticket-app-docker.jar
ENTRYPOINT ["java","-jar","/ticket-app-docker.jar"]