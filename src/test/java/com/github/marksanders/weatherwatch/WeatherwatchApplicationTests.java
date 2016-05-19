package com.github.marksanders.weatherwatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeatherwatchApplication.class)
@WebAppConfiguration
public class WeatherwatchApplicationTests {
    
    @Autowired
    private UriComponentsBuilder builder;

    @Autowired
    private UriComponentsBuilder anotherBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate anotherRestTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void builderConstructed() {
        assertNotNull(builder);

        assertEquals("http://api.openweathermap.org/data/2.5/weather?appid=0123456789abcdeffedcba9876543210", builder.build().toString());
    }

    @Test
    public void anotherBuilderConstructed() {
        assertNotNull(anotherBuilder);

        anotherBuilder.queryParam("id", "123456");
        assertEquals(
                "http://api.openweathermap.org/data/2.5/weather?appid=0123456789abcdeffedcba9876543210&id=123456", 
                anotherBuilder.build().toString());
    }
    @Test
    public void builderHasPrototypeScope() {
        assertNotEquals(builder, anotherBuilder);
    }

    @Test
    public void restTemplateConstructed() {
        assertNotNull(restTemplate);
    }

    @Test
    public void restTemplateHasPrototypeScope() {
        assertNotEquals(restTemplate, anotherRestTemplate);
    }

    @Test
    public void requestFactorySingletonBean() {
        assertNotEquals(restTemplate, anotherRestTemplate);
        assertEquals(restTemplate.getRequestFactory(), anotherRestTemplate.getRequestFactory());
    }

}
