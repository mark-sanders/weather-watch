package com.github.marksanders.weatherwatch.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.marksanders.weatherwatch.WeatherwatchApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeatherwatchApplication.class)
@WebAppConfiguration
public class WeatherServiceImplConfigurationTest {
    
    @Resource
    private WeatherServiceImpl weatherService;

    @Test
    public void testWeatherService() {
        assertNotNull(weatherService);
        assertEquals("http://api.openweathermap.org/data/2.5/weather", weatherService.getWeatherwatchUrl());
        assertEquals("0123456789abcdeffedcba9876543210", weatherService.getApiKey());
        assertNotNull(weatherService.getRequestFactory());
    }

    @Test
    public void testUriComponentsBuilder() {
        UriComponentsBuilder builder = weatherService.createUriComponentsBuilder();
        assertNotNull(builder);
        builder.queryParam("id", "123456");
        assertEquals(
                "http://api.openweathermap.org/data/2.5/weather?appid=0123456789abcdeffedcba9876543210&id=123456", 
                builder.build().toString());
    }


    @Test
    public void testRestTemplate() {
        RestTemplate restTemplate = weatherService.createRestTemplate();
        assertNotNull(restTemplate);
    }

}
