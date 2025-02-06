package org.example.authservice.kafka;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.NotifyDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthProducer {

    private static final String TOPIC = "auth-notification";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendNotification(NotifyDTO message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
