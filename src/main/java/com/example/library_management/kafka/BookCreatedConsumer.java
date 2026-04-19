package com.example.library_management.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookCreatedConsumer {
    @KafkaListener(topics = "book-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println("Received: " + message);
    }
}
