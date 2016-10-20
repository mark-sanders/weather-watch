package com.github.marksanders.weatherwatch.api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class WeatherResultTest {

    @Test
    public void testWeatherMethod() {
        
        WeatherJson weather1 = new WeatherJson();
        WeatherJson weather2 = new WeatherJson();
        WeatherJson weather3 = new WeatherJson();
        List<WeatherJson> weatherList = Arrays.asList(weather1, weather2, weather3);

        WeatherResultJson weatherResult = 
                new WeatherResultJson("city", 123456, System.currentTimeMillis(), weatherList, null, null);
        
        assertEquals("Expecting no weather detail", weather1, weatherResult.getWeather().get());
    }

    @Test
    public void testWeatherMethodEmptyList() {
        
        WeatherResultJson weatherResult = 
                new WeatherResultJson("city", 123456, System.currentTimeMillis(), Collections.emptyList(), null, null);

        assertEquals("Expecting no weather detail", Optional.empty(), weatherResult.getWeather());
    }

    @Test
    public void testWeatherMethodNullList() {
        
        WeatherResultJson weatherResult = 
                new WeatherResultJson("city", 123456, System.currentTimeMillis(), null, null, null);

        assertEquals("Expecting no weather detail", Optional.empty(), weatherResult.getWeather());
    }

}
