package com.fizalise.notificationservice.service;

import com.fizalise.orderservice.event.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @KafkaListener(topics = "order-notification")
    public void handleTopic(OrderPlacedEvent orderPlacedEvent) {
        System.out.printf("Получен объект: %s%n", orderPlacedEvent);
    }
}
