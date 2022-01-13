package com.example.bentou;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            // https://stackoverflow.com/questions/44176335/restclientexception-could-not-extract-response-no-suitable-httpmessageconverte
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            RestTemplate restTemplate = new RestTemplateBuilder().build();
            restTemplate.setMessageConverters(messageConverters);
            return restTemplate;
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(BentouApplication.class, args);
    }
}
