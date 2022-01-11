package com.example.bentou;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
@AutoConfigureMockMvc
class BentouControllerTest {
    @TestConfiguration
    public static class ConfigurationUme {
        @Bean("bentouController")
        public BentouController controller(@Qualifier("ume") OnigiriService service) {
            return new BentouController(service);
        };
    }

    @Autowired MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/bentou"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String expected = "{\"name\":\"おにぎり\",\"guzai\":\"梅\"}";  // 梅おにぎり
        String actual = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
    }
}