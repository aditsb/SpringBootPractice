package com.springboot.practice.core.reactive;

public class OrderConsumer implements java.util.function.Consumer<String> {

    @Override
    public void accept(String order) {
        System.out.println(order);
    }
}
