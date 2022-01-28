package com.springboot.practice.core.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class StringJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before Job is executed");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After Job is executed"+jobExecution.getStatus());

    }
}
