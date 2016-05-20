package com.github.marksanders.weatherwatch.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class WeatherResultTest {

    @Test
    public void testWeatherMethod() {
        
        Weather weather1 = new Weather();
        Weather weather2 = new Weather();
        Weather weather3 = new Weather();
        List<Weather> weatherList = Arrays.asList(weather1, weather2, weather3);

        WeatherResult weatherResult = 
                new WeatherResult("city", 123456, System.currentTimeMillis(), weatherList, null, null);
        
        assertEquals("Expecting no weather detail", weather1, weatherResult.getWeather());
    }

    @Test
    public void testWeatherMethodEmptyList() {
        
        WeatherResult weatherResult = 
                new WeatherResult("city", 123456, System.currentTimeMillis(), Collections.emptyList(), null, null);

        assertNull("Expecting no weather detail", weatherResult.getWeather());
    }

    @Test
    public void testWeatherMethodNullList() {
        
        WeatherResult weatherResult = 
                new WeatherResult("city", 123456, System.currentTimeMillis(), null, null, null);

        assertNull("Expecting no weather detail", weatherResult.getWeather());
    }

}
