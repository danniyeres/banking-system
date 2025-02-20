package org.example.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationservice.dto.TransactionNotify;
import org.example.notificationservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TransactionListener {
    private final NotificationService notificationService;

    @KafkaListener(topics = "transaction-notification", groupId = "notification-service-group")
    public void consume(String message) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();

        try {
            TransactionNotify transactionNotify = objectMapper.readValue(message, TransactionNotify.class);
            notificationService.sendTransactionNotification(transactionNotify.getEmail(), "Transaction Notification", transactionNotify.getMessage());
            log.info("Consuming message: {}", transactionNotify);
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            throw e;
        }
    }
}
