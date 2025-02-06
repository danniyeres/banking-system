package org.example.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuthListener {

    @KafkaListener (topics = "auth-notification", groupId = "notification-service-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
