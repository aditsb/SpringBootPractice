package com.springboot.practice.core.controllers;

import com.springboot.practice.core.service.VaccineService;
import com.springboot.practice.core.vo.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class VaccineReactiveController {
    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/vaccines")
    public Flux<Vaccine> getVaccines() {
        return vaccineService.getVaccines();
    }
}
