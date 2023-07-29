package com.example.spark.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class KafkaProperties {

    @Value("${kafka.bootstrapServers}")
    private String bootstrapServers;

    @Value("${kafka.topic}")
    private String topic;
}
