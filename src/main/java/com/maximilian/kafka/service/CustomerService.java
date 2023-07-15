package com.maximilian.kafka.service;

import com.maximilian.kafka.config.KafkaTopicConfig;
import com.maximilian.kafka.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    private final KafkaTemplate<String, Customer> kafkaTemplate;
    private final KafkaTopicConfig kafkaTopicConfig;

    public CustomerService(KafkaTemplate<String, Customer> kafkaTemplate,
                           KafkaTopicConfig kafkaTopicConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicConfig = kafkaTopicConfig;
    }

    public void save(Customer customer) {
        log.info("Saving {}", customer);
        kafkaTemplate.send(kafkaTopicConfig.getTopicName(), customer)
                .addCallback(result -> {
                    if (result != null) {
                        log.info("Successfully sent, key = " + result.getProducerRecord().key());
                    }
                }, ex -> log.error("Got error ", ex));
    }

}
