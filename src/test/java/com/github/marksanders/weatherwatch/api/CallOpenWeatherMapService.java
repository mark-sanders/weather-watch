package com.github.marksanders.weatherwatch.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class CallOpenWeatherMapService {

    // epsilon error value for comparing doubles
    private static final double EPSILON = 0.00001;
    private static final String WIREMOCKURL = "http://localhost:8080";
    private static final String GOOD_API_KEY = "&appid=0123456789abcdeffedcba9876543210";
    private static final String BAD_API_KEY =  "&appid=99999999999999999999999999999999";

    private static final String GET_WEATHER = "/data/2.5/weather";
    private static final String GET_LONDON_WEATHER_PATH = GET_WEATHER + "?id=2643743";
    private static final String GET_HONG_KONG_WEATHER_PATH = GET_WEATHER + "?id=1819729";
    private static final String GET_UNKNOWN_WEATHER_PATH = GET_WEATHER + "?id=999999";

    @Rule
    public WireMockRule wireMock = new WireMockRule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    

    @Test
    public void testLondonWeatherPojo() {
        wireMock.stubFor(get(urlEqualTo(GET_LONDON_WEATHER_PATH + GOOD_API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBodyFile("london.json")));

        RestTemplate restTemplate = new RestTemplate();
        WeatherResult weatherResult = restTemplate.getForObject(WIREMOCKURL + GET_LONDON_WEATHER_PATH + GOOD_API_KEY, WeatherResult.class);
        assertNotNull("Expecting weather result", weatherResult);
        assertEquals("check name", "London", weatherResult.getName());
        
        assertNotNull("check timestamp provided", weatherResult.getTimestamp());
        assertEquals("check timestamp", 1463675149L, weatherResult.getTimestamp());
        
        assertNotNull("Expecting weather details", weatherResult.getWeatherList());
        assertEquals("Expecting single weather detail", 1, weatherResult.getWeatherList().size());

        Weather weather = weatherResult.getWeather();
        assertEquals(weatherResult.getWeatherList().get(0), weather);
        assertNotNull("Expecting weather detail", weather);
        assertEquals("Clouds", weather.getMain());
        assertEquals("broken clouds", weather.getDescription());
        assertEquals("04d", weather.getIcon());

        WeatherSys sys = weatherResult.getSys();
        assertNotNull("Expecting sys", sys);
        assertEquals("GB", sys.getCountry());
        assertEquals(1463630501L, sys.getSunrise());
        assertEquals(1463687600L, sys.getSunset());

        WeatherMain main = weatherResult.getMain();
        assertNotNull("Expecting main weather", main);
        assertEquals(290.93, main.getTemp().doubleValue(), EPSILON);
        assertEquals(1012, main.getPressure().intValue());
        assertEquals(55, main.getHumidity().intValue());
        assertEquals(288.95, main.getTempMin().doubleValue(), EPSILON);
        assertEquals(292.59, main.getTempMax().doubleValue(), EPSILON);

        weatherResult.setWeatherList(new ArrayList<>());
        assertNull("Expecting no weather detail", weatherResult.getWeather());

        weatherResult.setWeatherList(null);
        assertNull("Expecting no weather detail", weatherResult.getWeather());

   }

    @Test
    public void testHongKongWeatherPojo() {
        wireMock.stubFor(get(urlEqualTo(GET_HONG_KONG_WEATHER_PATH + GOOD_API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBodyFile("hong-kong.json")));

        RestTemplate restTemplate = new RestTemplate();
        WeatherResult weather = restTemplate.getForObject(WIREMOCKURL + GET_HONG_KONG_WEATHER_PATH + GOOD_API_KEY, WeatherResult.class);
        assertNotNull("Expecting weather result", weather);
        assertEquals("check name", "Hong Kong", weather.getName());
        assertEquals("check timestamp", 1463677208L, weather.getTimestamp());
    }

    @Test
    public void testUnauthorized() {
        wireMock.stubFor(get(urlEqualTo(GET_LONDON_WEATHER_PATH + BAD_API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(401)
                        .withBodyFile("unauthorized.json")));
        
        thrown.expect(HttpClientErrorException.class);
        thrown.expectMessage("401 Unauthorized");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(WIREMOCKURL + GET_LONDON_WEATHER_PATH + BAD_API_KEY, WeatherResult.class);
    }

    @Test
    public void testNotFound() {
        wireMock.stubFor(get(urlEqualTo(GET_UNKNOWN_WEATHER_PATH + GOOD_API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(404)
                        .withBodyFile("not-found.json")));
        
        thrown.expect(HttpClientErrorException.class);
        thrown.expectMessage("404 Not Found");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(WIREMOCKURL + GET_UNKNOWN_WEATHER_PATH + GOOD_API_KEY, WeatherResult.class);
    }

    @Test
    public void testOther() {
        wireMock.stubFor(get(urlEqualTo(GET_UNKNOWN_WEATHER_PATH + GOOD_API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(404)
                        .withBodyFile("not-found.json")));
        
        thrown.expect(HttpClientErrorException.class);
        thrown.expectMessage("404 Not Found");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(WIREMOCKURL + GET_UNKNOWN_WEATHER_PATH + GOOD_API_KEY, WeatherResult.class);
        } catch (HttpClientErrorException e) {
            String response = e.getResponseBodyAsString();
            System.out.println(response);
            throw e;
        }
    }

}
