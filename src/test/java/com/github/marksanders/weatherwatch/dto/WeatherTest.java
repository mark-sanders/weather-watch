package com.github.marksanders.weatherwatch.dto;


import static org.junit.Assert.assertEquals;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class WeatherTest {
    
    @Test
    public void testBuilderLondon() {
        
        final ZoneId bst = ZoneId.of("Europe/London");
        final Clock clock = Clock.fixed(Instant.ofEpochSecond(1463675149L), bst);
        final LocalDate today = LocalDate.now(clock);
        final LocalTime sunrise = LocalTime.of(5, 14);
        final LocalTime sunset = LocalTime.of(20, 55);

        Weather.Builder builder = new Weather.Builder();
        Weather weatherDto = builder
                .cityName("London")
                .cityId(123456)
                .description("Little fluffy clouds")
                .temperatureCelsius(16)
                .cityDateTime(ZonedDateTime.now(clock))
                .citySunriseDateTime(ZonedDateTime.of(today, sunrise, bst))
                .citySunsetDateTime(ZonedDateTime.of(today, sunset, bst))
                .build();
        
        assertEquals("London", weatherDto.getCityName());
        assertEquals(123456, weatherDto.getCityId());
        assertEquals("Little fluffy clouds", weatherDto.getDescription());
        assertEquals(Temperature.fromCelsius(16), weatherDto.getTemperature());
    }

    @Test
    public void testBuilderLondonFromSeconds() {
        
        final ZoneId bst = ZoneId.of("Europe/London");

        Weather.Builder builder = new Weather.Builder();
        Weather weatherDto = builder
                .cityName("London")
                .cityId(123456)
                .description("Little fluffy clouds")
                .temperatureCelsius(16)
                .timeZone(bst)
                .cityDateTime(1463675149L)
                .citySunriseDateTime(1463630501L)
                .citySunsetDateTime(1463687600)
                .build();
        
        assertEquals("London", weatherDto.getCityName());
        assertEquals(123456, weatherDto.getCityId());
        assertEquals("Little fluffy clouds", weatherDto.getDescription());
        assertEquals(Temperature.fromCelsius(16), weatherDto.getTemperature());
    }

}
