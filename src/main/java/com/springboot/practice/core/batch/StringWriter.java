package com.springboot.practice.core.batch;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class StringWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends  String> items) {
        System.out.println("Inside write");
        System.out.println("write data:"+items);

    }
}
