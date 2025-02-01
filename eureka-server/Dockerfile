FROM alpine:latest
RUN apk add --no-cache openjdk17-jdk
WORKDIR /app
EXPOSE 8761
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
CMD ["java", "-jar", "eureka-server.jar"]
