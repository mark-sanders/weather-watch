package com.github.marksanders.weatherwatch.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class JsonMappingTest {

    // epsilon error value for comparing doubles
    private static final double EPSILON = 0.00001;
    

    @Test
    public void mapLondonWeatherPojo() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("__files/london.json").getFile());
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        WeatherResultJson weatherResult = mapper.readValue(file, WeatherResultJson.class);
        assertNotNull("Expecting weather result", weatherResult);
        assertEquals("check name", "London", weatherResult.getName());
        assertEquals("check id", 2643743, weatherResult.getId());
        
        assertEquals("check timestamp", 1463675149L, weatherResult.getDt());
        
        assertNotNull("Expecting weather details", weatherResult.getWeatherList());
        assertEquals("Expecting single weather detail", 1, weatherResult.getWeatherList().size());
        
        // check weather property convenience method works
        assertEquals(weatherResult.getWeatherList().get(0), weatherResult.getWeather().get());

        WeatherJson weather = weatherResult.getWeather().get();
        assertNotNull("Expecting weather detail", weather);
        assertEquals("Clouds", weather.getMain());
        assertEquals("broken clouds", weather.getDescription());
        assertEquals("04d", weather.getIcon());

        WeatherDetailsJson sys = weatherResult.getDetails().get();
        assertNotNull("Expecting sys", sys);
        assertEquals("GB", sys.getCountry());
        assertEquals(1463630501L, sys.getSunrise());
        assertEquals(1463687600L, sys.getSunset());

        WeatherMainJson main = weatherResult.getMain().get();
        assertNotNull("Expecting main weather", main);
        assertEquals(290.93, main.getTemp().get().doubleValue(), EPSILON);
        assertEquals(1012, main.getPressure().get().intValue());
        assertEquals(55, main.getHumidity().get().intValue());
        assertEquals(288.95, main.getTempMin().get().doubleValue(), EPSILON);
        assertEquals(292.59, main.getTempMax().get().doubleValue(), EPSILON);
    }
}
