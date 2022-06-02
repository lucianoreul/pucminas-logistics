package com.ms.logistics.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(
            topics = "logistics",
            groupId = "groupId"
    )
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}
