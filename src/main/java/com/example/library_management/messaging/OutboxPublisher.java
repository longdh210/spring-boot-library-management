package com.example.library_management.messaging;

import com.example.library_management.entity.OutboxEvent;
import com.example.library_management.repository.OutboxEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class OutboxPublisher {
    @Autowired
    private OutboxEventRepository outboxEventRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publish() throws ExecutionException, InterruptedException {
        List<OutboxEvent> outboxEvents = outboxEventRepository.findByPublishedFalse();

        for(OutboxEvent outboxEvent : outboxEvents) {
            try {
                System.out.println("Outbox start send...");
                kafkaTemplate.send("book-topic", outboxEvent.getPayload()).get();

                outboxEvent.setPublished(true);
                outboxEventRepository.save(outboxEvent);
            } catch(Exception e) {
                System.out.println("Outbox throw error..." + e);
                throw e;
            }
        }
    }
}
