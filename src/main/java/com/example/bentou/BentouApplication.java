package com.example.bentou;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BentouApplication {
    @Configuration
    public static class BentouApplicationConfiguration {
        @Bean
        MeterRegistry meterRegistry() {
            return new SimpleMeterRegistry();
        }
        @Bean
        RestTemplate restTemplate() {
            return new RestTemplateBuilder().build();
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(BentouApplication.class, args);
    }
}
