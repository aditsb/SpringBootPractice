package com.springboot.practice.core.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StringBatchConfiguration {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("Job 1").incrementer(new RunIdIncrementer()).listener(listener()).start(step()).build();

    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step 1").<String, String>chunk(1)
                .reader(reader())
                .writer(writer())
                .processor(processor()).build();

    }

    @Bean
    public StringReader reader() {
        return new StringReader();

    }

    @Bean
    public StringWriter writer() {
        return new StringWriter();

    }

    @Bean
    public StringProcessor processor() {
        return new StringProcessor();

    }

    @Bean
    public StringJobListener listener() {
        return new StringJobListener();

    }
}
