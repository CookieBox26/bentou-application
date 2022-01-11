package com.example.bentou;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OnigiriServiceTunaTest {
    @Autowired OnigiriServiceTuna service;
    @Autowired MeterRegistry meterRegistry;
    @MockBean RestTemplate restTemplate;

    // RestTemplate から /abc にアクセスすると最初だけ成功して後は失敗するようにモック
    @BeforeEach
    public void setUp() {
        when(restTemplate.getForObject("abc", String.class))
                .thenReturn("OK", "OK", "OK")
                .thenThrow(new IllegalArgumentException("ERROR"));
    }

    @Test
    public void test() {
        assertEquals("OK", service.get());  // 1
        assertEquals("OK", service.get());  // 2
        assertEquals("OK", service.get());  // 3
        assertEquals("NG", service.get());  // 4

        assertEquals(0, meterRegistry.get("resilience4j.circuitbreaker.state")
                .tags("name", "hoge", "state", "open").gauge().value(), 1e-6);

        assertEquals("NG", service.get());  // 5

        assertEquals(1, meterRegistry.get("resilience4j.circuitbreaker.state")
                .tags("name", "hoge", "state", "open").gauge().value(), 1e-6);

        try {
            assertEquals("NG", service.get());  // 6
            fail();
        } catch (CallNotPermittedException exception) {
        }
    }
}