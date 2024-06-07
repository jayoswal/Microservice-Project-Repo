FROM openjdk:17
EXPOSE 8080
COPY target/configserver-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java","-jar","configserver-0.0.1-SNAPSHOT.jar"]