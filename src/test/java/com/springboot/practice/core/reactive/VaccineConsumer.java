package com.springboot.practice.core.reactive;

import com.springboot.practice.core.vo.Vaccine;

public class VaccineConsumer implements java.util.function.Consumer<com.springboot.practice.core.vo.Vaccine>{

    @Override
    public void accept(Vaccine vaccine) {
        System.out.println(vaccine.getName());
        System.out.println(vaccine.isDelivered());

    }
}
