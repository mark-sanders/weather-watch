package com.github.marksanders.weatherwatch.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;

import com.github.marksanders.weatherwatch.api.WeatherResultJson;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class OpenWeatherMapServiceImplTest {
    private static final String WIREMOCKURL = "http://localhost:8080";
    private static final String API_KEY = "0123456789abcdeffedcba9876543210";
    private static final String BAD_API_KEY =  "99999999999999999999999999999999";

    private static final String GET_WEATHER = "/data/2.5/weather";

    @Rule
    public WireMockRule wireMock = new WireMockRule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private OpenWeatherMapServiceImpl uut = new OpenWeatherMapServiceImpl();

    @Before
    public void setup() {
        uut.setRequestFactory(new SimpleClientHttpRequestFactory());
        uut.setWeatherwatchUrl(WIREMOCKURL + GET_WEATHER);
        uut.setApiKey(API_KEY);
        
    }

    @Test
    public void testLondonWeather() {
        wireMock.stubFor(
                get(urlPathEqualTo(GET_WEATHER))
                        .withQueryParam("id", equalTo("2643743"))
                        .withQueryParam("appid", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBodyFile("london.json")));

        WeatherResultJson weatherResult = uut.getWeatherForCity(2643743).get();

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

        WeatherResultJson weatherResult = uut.getWeatherForCity(1819729).get();

        assertNotNull("Expecting weather result", weatherResult);
        assertEquals("check name", "Hong Kong", weatherResult.getName());
        assertEquals("check id", 1819729, weatherResult.getId());
    }

    @Test
    public void testUnauthorized() {
        wireMock.stubFor(
                get(urlPathEqualTo(GET_WEATHER))
                        .withQueryParam("id", equalTo("2643743"))
                        .withQueryParam("appid", equalTo(BAD_API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(401)
                        .withBodyFile("unauthorized.json")));
        
        thrown.expect(HttpClientErrorException.class);
        thrown.expectMessage("401 Unauthorized");

        uut.setApiKey(BAD_API_KEY);
        uut.getWeatherForCity(2643743);
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

        uut.getWeatherForCity(999999);
    }
}
