package com.example.bentou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BentouController {
    private final OnigiriService onigiriService;

    @Autowired
    public BentouController(@Qualifier("tuna") OnigiriService onigiriService) {
        this.onigiriService = onigiriService;
    }

    @GetMapping("/bentou")
    public Bentou provide() {
        Bentou bentou = new Bentou();
        bentou.onigiri = this.onigiriService.provideOnigiri();
        return bentou;
    }
}
