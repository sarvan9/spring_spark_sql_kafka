package com.example.spark.config;

import com.example.spark.domain.SparkProperties;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {

    @Autowired
    private SparkProperties sparkProperties;

    @Bean
    public SparkSession getSparkSession(){
        SparkConf sparkConf = new SparkConf().setAppName("Kafka Extractor");
        SparkSession ss = null;
        try {
            ss = SparkSession.builder()
                    .master(sparkProperties.getMaster())
                    .appName(sparkProperties.getApplicationName())
                    .getOrCreate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ss;
    }
}
