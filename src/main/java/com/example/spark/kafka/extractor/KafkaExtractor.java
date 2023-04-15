package com.example.spark.kafka.extractor;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaExtractor {

    @Autowired
    SparkSession spark;

    public Dataset<Row> extract() {
        Dataset<Row> dataset = spark.read()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9093")
                .option("subscribe", "test_topic")
                .load()
                .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
        return dataset;
    }
}
