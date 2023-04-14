package com.example.spark.kafka.runner;

import com.example.spark.kafka.extractor.KafkaExtractor;
//import lombok.extern.slf4j.Slf4j;
import com.example.spark.publish.EventPublisher;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class KafkaDatasourceRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatasourceRunner.class);

    @Autowired
    private KafkaExtractor extractor;

    @Autowired
    private EventPublisher eventPublisher;

    @PostConstruct
    public void init(){
        LOGGER.info("Inside init(), publishing initial events.");
        eventPublisher.publishEvents(20);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Inside run, before extracting events");
        Dataset<Row> dataset = extractor.extract();

        LOGGER.info("No of events fetched : {}",dataset.count());
        dataset.foreach(row -> {
            String key = row.getAs("key");
            String value = row.getAs("value");

            LOGGER.info("Key : {} , Value : {}", key, value);
        });
    }
}
