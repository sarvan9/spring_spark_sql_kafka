package com.example.spark.kafka.handler;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProducerHandler {

    private KafkaProducer getProducer(){

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        return new KafkaProducer<String, String>(properties);
    }

    public void publishEvent(ProducerRecord<String, String> producerRecord, Callback error_while_producing){
        getProducer().send(producerRecord);
    }


}
