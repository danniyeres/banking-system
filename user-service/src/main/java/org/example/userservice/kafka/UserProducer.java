package org.example.userservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class UserProducer {

    private static final String TOPIC = "user-notification";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendNotification(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
