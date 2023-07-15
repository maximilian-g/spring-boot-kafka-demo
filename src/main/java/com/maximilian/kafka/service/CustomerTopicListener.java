package com.maximilian.kafka.service;

import com.maximilian.kafka.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerTopicListener {

    @KafkaListener(topics = "${customer.topic.name}")
    public void consume(Customer customer) {
        log.info("Got message {}", customer);
    }

}
