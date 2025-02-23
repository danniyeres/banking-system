FROM alpine:latest
RUN apk add --no-cache openjdk17-jdk
WORKDIR /app
EXPOSE 8085
COPY target/transaction-service-0.0.1-SNAPSHOT.jar transaction-service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "transaction-service-0.0.1-SNAPSHOT.jar"]