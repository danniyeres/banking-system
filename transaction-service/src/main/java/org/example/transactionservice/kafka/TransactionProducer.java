package org.example.transactionservice.kafka;

import lombok.RequiredArgsConstructor;
import org.example.transactionservice.dto.TransactionNotify;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionProducer {

    private static final String TOPIC = "transaction-notification";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendNotification(TransactionNotify message) {
        kafkaTemplate.send(TOPIC, message);
    }}
