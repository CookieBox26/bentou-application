package com.example.bentou;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class KomeRepository {
    private final String uri;
    private final RestTemplate restTemplate;

    public KomeRepository(
            @Value("${kome.uri}") String uri,
            RestTemplate restTemplate
    ) {
        this.uri = uri;
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "kome", fallbackMethod = "fallback")
    public String get() {
        return restTemplate.getForObject(this.uri, String.class);
    }
    private String fallback(IllegalArgumentException e) {
        return "NG";
    }

    @CircuitBreaker(name = "kome", fallbackMethod = "fallbackKome")
    public Kome getKome() {
        return restTemplate.getForObject(this.uri, Kome.class);
    }
    private Kome fallbackKome(IllegalArgumentException e) {
        return null;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Kome {
        public String meigara;
    }
}
