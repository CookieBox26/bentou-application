package com.example.bentou;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("tuna")
public class OnigiriServiceTuna implements OnigiriService {
    private final RestTemplate restTemplate;
    public OnigiriServiceTuna(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Onigiri provideOnigiri() {
        return new Onigiri("ツナ");
    }

    @Override
    @CircuitBreaker(name = "hoge", fallbackMethod = "fallback")
    public String get() {
        return restTemplate.getForObject("abc", String.class);
    }
    private String fallback(IllegalArgumentException e) {
        return "NG";
    }

    @CircuitBreaker(name = "hoge", fallbackMethod = "fallback")
    private String get2() {
        return restTemplate.getForObject("abc", String.class);
    }
}
