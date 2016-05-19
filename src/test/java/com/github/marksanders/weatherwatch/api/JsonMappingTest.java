package com.github.marksanders.weatherwatch.api;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMappingTest {

    // epsilon error value for comparing doubles
    private static final double EPSILON = 0.00001;
    

    @Test
    public void mapLondonWeatherPojo() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("__files/london.json").getFile());
        
        ObjectMapper mapper = new ObjectMapper();
        WeatherResult weatherResult = mapper.readValue(file, WeatherResult.class);
        assertNotNull("Expecting weather result", weatherResult);
        assertEquals("check name", "London", weatherResult.getName());
        
        assertNotNull("check timestamp provided", weatherResult.getTimestamp());
        assertEquals("check timestamp", 1463675149L, weatherResult.getTimestamp());
        
        assertNotNull("Expecting weather details", weatherResult.getWeatherList());
        assertEquals("Expecting single weather detail", 1, weatherResult.getWeatherList().size());
        
        // check weather property convenience method works
        assertEquals(weatherResult.getWeatherList().get(0), weatherResult.getWeather());

        Weather weather = weatherResult.getWeather();
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

        // check weather property short cut works
        weatherResult.setWeatherList(new ArrayList<>());
        assertNull("Expecting no weather detail", weatherResult.getWeather());

        weatherResult.setWeatherList(null);
        assertNull("Expecting no weather detail", weatherResult.getWeather());

    }
}
