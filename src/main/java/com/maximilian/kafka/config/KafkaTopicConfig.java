package com.maximilian.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${customer.topic.name}")
    private String topicName;
    @Value("${customer.topic.partitions.amount}")
    private int partitions;

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, partitions, (short) 2);
    }

    public String getTopicName() {
        return topicName;
    }

    public int getPartitions() {
        return partitions;
    }
}
