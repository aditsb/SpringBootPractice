package com.springboot.practice.core.service;

import com.springboot.practice.core.vo.Vaccine;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class VaccineService {

    public Flux<Vaccine> getVaccines() {
        return Flux.just(new Vaccine("Pfizer"), new Vaccine("J&J"),
                new Vaccine("CoviShield"), new Vaccine("CoVaccine"));
    }
}
