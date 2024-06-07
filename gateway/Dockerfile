FROM openjdk:17
EXPOSE 8084
COPY target/gateway-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java","-jar","gateway-0.0.1-SNAPSHOT.jar"]