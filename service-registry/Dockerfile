FROM openjdk:17
EXPOSE 8761
COPY target/service-registry-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java","-jar","service-registry-0.0.1-SNAPSHOT.jar"]