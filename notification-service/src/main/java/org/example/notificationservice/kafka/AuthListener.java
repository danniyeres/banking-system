package org.example.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationservice.dto.AuthNotify;
import org.example.notificationservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthListener {

    private final NotificationService notificationService;

    @KafkaListener (topics = "auth-notification", groupId = "notification-service-group")
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            AuthNotify authNotify = objectMapper.readValue(message, AuthNotify.class);
            notificationService.sendNotification(authNotify.getEmail(), "Auth Notification", authNotify.getMessage());
            log.info("Consuming message: {}", authNotify);
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            throw e;
        }

    }
}
