package com.example.spark;

import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SparkApplication.class, args);
	}

}
