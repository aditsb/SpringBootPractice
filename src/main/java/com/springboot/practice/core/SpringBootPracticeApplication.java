package com.springboot.practice.core;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@EnableBatchProcessing
public class SpringBootPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
	}

}
