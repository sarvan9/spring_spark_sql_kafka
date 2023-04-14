package com.example.spark.kafka.runner;

import com.example.spark.kafka.extractor.KafkaExtractor;
//import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
//@Slf4j
public class KafkaDatasourceRunner implements ApplicationRunner {

    @Autowired
    private KafkaExtractor extractor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Dataset<Row> dataset = extractor.extract();
        dataset.foreach(row -> {
            String key = row.getAs("key");
            String value = row.getAs("value");
        });
    }
}
