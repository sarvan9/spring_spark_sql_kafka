package com.example.spark.domain;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {

    @Bean
    public SparkSession getSparkSession(){
        SparkConf sparkConf = new SparkConf().setAppName("Kafka Extractor");
        SparkSession ss = null;
        try {
            ss = SparkSession.builder()
                    .master("local[1]")
                    .appName("Test")
                    .getOrCreate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ss;
    }
}
