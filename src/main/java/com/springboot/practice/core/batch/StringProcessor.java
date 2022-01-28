package com.springboot.practice.core.batch;

import org.springframework.batch.item.ItemProcessor;

public class StringProcessor implements ItemProcessor<String,String> {
    @Override
    public String process(String item) {
        System.out.println("Inside the process");
        return "Processed:"+item.toUpperCase();
    }
}
