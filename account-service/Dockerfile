FROM alpine:latest
RUN apk add --no-cache openjdk17-jdk
WORKDIR /app
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "account-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083
