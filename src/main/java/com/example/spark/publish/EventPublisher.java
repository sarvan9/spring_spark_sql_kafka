package com.example.spark.publish;

import com.example.spark.domain.KafkaProperties;
import com.example.spark.kafka.handler.KafkaProducerHandler;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

    @Autowired
    KafkaProducerHandler producerHandler;

    @Autowired
    KafkaProperties kafkaProperties;

    public void publishEvents(int eventsCount) {

        for (int i = 0; i < eventsCount; i++) {

            UUID key = UUID.randomUUID();

            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(kafkaProperties.getTopic(), key.toString(), "Event" + i);

            producerHandler.publishEvent(producerRecord, (metadata, ex) -> {
                if (ex == null) {
                    // the record was successfully sent
                    LOGGER.info("Received new metadata. \n" +
                            "Topic:" + metadata.topic() + "\n" +
                            "Partition: " + metadata.partition() + "\n" +
                            "Offset: " + metadata.offset() + "\n" +
                            "Timestamp: " + metadata.timestamp());
                } else {
                    LOGGER.error("Error while publishing event : {}", ex.getLocalizedMessage());
                }
            });

        }
    }
}
