package com.springboot.practice.core.controllers;

import com.springboot.practice.core.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VaccineWebController {
    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/vaccinesTemplate1")
    public Mono<String> getVaccines(Model model) {
        model.addAttribute("vaccines",vaccineService.getVaccines());
        return Mono.just("VaccineDisplay");
    }
}
