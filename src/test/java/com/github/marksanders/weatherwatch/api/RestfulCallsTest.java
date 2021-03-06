package com.github.marksanders.weatherwatch.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class RestfulCallsTest {

    private static final String API_KEY = "0123456789abcdeffedcba9876543210";
    private static final String WIREMOCKURL = "http://localhost:8080";
    private static final String GOOD_API_KEY = "&appid=" + API_KEY;
    private static final String BAD_API_KEY =  "&appid=999";

    private static final String GET_WEATHER = "/data/2.5/weather";
    private static final String GET_LONDON_WEATHER_PATH = GET_WEATHER + "?id=2643743";
    private static final String GET_HONG_KONG_WEATHER_PATH = GET_WEATHER + "?id=1819729";
    private static final String GET_UNKNOWN_WEATHER_PATH = GET_WEATHER + "?id=999999";

    @Rule
    public WireMockRule wireMock = new WireMockRule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    

    @Test
    public void testLondonWeather() {
        wireMock.stubFor(
                get(urlPathEqualTo(GET_WEATHER))
                        .withQueryParam("id", equalTo("2643743"))
                        .withQueryParam("appid", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBodyFile("london.json")));

        RestTemplate restTemplate = new RestTemplate();
        WeatherResultJson weatherResult = restTemplate.getForObject(WIREMOCKURL + GET_LONDON_WEATHER_PATH + GOOD_API_KEY, WeatherResultJson.class);
        assertNotNull("Expecting weather result", weatherResult);
        assertEquals("check name", "London", weatherResult.getName());
        assertEquals("check id", 2643743, weatherResult.getId());
   }

    @Test
    public void testHongKongWeather() {
        wireMock.stubFor(
                get(urlPathEqualTo(GET_WEATHER))
                        .withQueryParam("id", equalTo("1819729"))
                        .withQueryParam("appid", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBodyFile("hong-kong.json")));

        RestTemplate restTemplate = new RestTemplate();
        WeatherResultJson weatherResult = restTemplate.getForObject(WIREMOCKURL + GET_HONG_KONG_WEATHER_PATH + GOOD_API_KEY, WeatherResultJson.class);
        assertNotNull("Expecting weather result", weatherResult);
        assertEquals("check name", "Hong Kong", weatherResult.getName());
        assertEquals("check id", 1819729, weatherResult.getId());
    }

    @Test
    public void testUnauthorized() {
        wireMock.stubFor(
                get(urlPathEqualTo(GET_WEATHER))
                        .withQueryParam("id", equalTo("2643743"))
                        .withQueryParam("appid", equalTo("999"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(401)
                        .withBodyFile("unauthorized.json")));
        
        thrown.expect(HttpClientErrorException.class);
        thrown.expectMessage("401 Unauthorized");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(WIREMOCKURL + GET_LONDON_WEATHER_PATH + BAD_API_KEY, WeatherResultJson.class);
    }

    @Test
    public void testNotFound() {
        wireMock.stubFor(
                get(urlPathEqualTo(GET_WEATHER))
                        .withQueryParam("id", equalTo("999999"))
                        .withQueryParam("appid", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(404)
                        .withBodyFile("not-found.json")));
        
        thrown.expect(HttpClientErrorException.class);
        thrown.expectMessage("404 Not Found");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(WIREMOCKURL + GET_UNKNOWN_WEATHER_PATH + GOOD_API_KEY, WeatherResultJson.class);
    }
}
