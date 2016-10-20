package com.github.marksanders.weatherwatch.weather;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.ZoneId;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.marksanders.weatherwatch.api.WeatherDetailsJson;
import com.github.marksanders.weatherwatch.api.WeatherJson;
import com.github.marksanders.weatherwatch.api.WeatherMainJson;
import com.github.marksanders.weatherwatch.api.WeatherResultJson;
import com.github.marksanders.weatherwatch.service.OpenWeatherMapService;
import com.github.marksanders.weatherwatch.service.TimeZoneService;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {
    private static final int OXFORD_ID = 123456;
    private static final int HANOI_ID = 987654;
    private static final ZoneId OXFORD_TZ = ZoneId.of("Europe/London");
    private static final ZoneId VIET_NAM_TZ = ZoneId.of("Asia/Ho_Chi_Minh");

    private WeatherServiceImpl uut = new WeatherServiceImpl();
    
    @Mock
    private TimeZoneService timeZoneService;

    @Mock
    private OpenWeatherMapService openWeatherMapService;
    
    @Before
    public void setup() {
        
        when(timeZoneService.getTimeZoneForCity(OXFORD_ID)).thenReturn(OXFORD_TZ);
        when(timeZoneService.getTimeZoneForCity(HANOI_ID)).thenReturn(VIET_NAM_TZ);
        uut.setTimeZoneService(timeZoneService);

        uut.setOpenWeatherMap(openWeatherMapService);
    }

    @Test
    public void testOxford() {
        WeatherJson weatherJson = new WeatherJson();
        WeatherDetailsJson details = new WeatherDetailsJson();
        WeatherMainJson main = new WeatherMainJson(290.0, 1101, 45, 280.0, 292.0);
        WeatherResultJson weatherResultJson = 
                new WeatherResultJson(
                        "Oxford", 
                        OXFORD_ID,
                        System.currentTimeMillis() / 1000L,
                        Collections.singletonList(weatherJson),
                        details,
                        main);

        when(openWeatherMapService.getWeatherForCity(OXFORD_ID)).thenReturn(weatherResultJson);
        
        Weather weather = uut.getWeatherForCity(OXFORD_ID);
        assertNotNull(weather);
    }

    @Test
    public void testVietnamTemperature() {
        WeatherJson weatherJson = new WeatherJson();
        WeatherDetailsJson details = new WeatherDetailsJson();
        WeatherMainJson main = new WeatherMainJson(300.0, 1101, 45, 295.0, 305.0);
        WeatherResultJson weatherResultJson = 
                new WeatherResultJson(
                        "Hanoi", 
                        HANOI_ID,
                        System.currentTimeMillis() / 1000L,
                        Collections.singletonList(weatherJson),
                        details,
                        main);

        when(openWeatherMapService.getWeatherForCity(HANOI_ID)).thenReturn(weatherResultJson);
        
        int temperature = uut.getTemperatureForCity(HANOI_ID);
        assertEquals(26, temperature);
    }

}
