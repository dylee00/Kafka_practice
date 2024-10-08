package com.kafka_pipeline_test.kafka_pipeline_test;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void create() {
        kafkaTemplate.send("topic", "say hello~!!!!");
    }

}