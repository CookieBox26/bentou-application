package com.example.bentou;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
public class BentouController {
    private final OnigiriService service;

    @Autowired
    public BentouController(@Qualifier("tuna") OnigiriService service) {
        this.service = service;
    }

    @GetMapping("/bentou")
    public Onigiri provide() {
        this.service.get();
        Onigiri onigiri = this.service.provideOnigiri();
        return onigiri;
    }
}
