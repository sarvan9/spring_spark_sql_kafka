package com.example.spark.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SparkProperties {

    @Value("${spark.appName}")
    private  String applicationName;

    @Value("${spark.master}")
    private String master;
}
