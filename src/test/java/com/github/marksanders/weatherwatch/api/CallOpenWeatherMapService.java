package com.github.marksanders.weatherwatch.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class CallOpenWeatherMapService {

    private static final String WIREMOCKURL = "http://localhost:8080";
    private static final String GET_LONDON_WEATHER_PATH = "/data/2.5/weather?id=2643743&appid=0123456789abcdeffedcba9876543210";
    @Rule
    public WireMockRule wireMock = new WireMockRule();

    @Test
    public void testRestTemplate() {
        wireMock.stubFor(get(urlEqualTo(GET_LONDON_WEATHER_PATH))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBodyFile("london.json")));
        RestTemplate restTemplate = new RestTemplate();
        Weather weather = restTemplate.getForObject(WIREMOCKURL + GET_LONDON_WEATHER_PATH, Weather.class);
        assertNotNull("Expecting weather result", weather);
        assertEquals("check name", "London", weather.getName());
    }

}
