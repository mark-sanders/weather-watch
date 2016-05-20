package com.github.marksanders.weatherwatch.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TemperatureTest {

    private static final double EPSILON = 0.00001;

    @Test
    public void testEquals() {
        Temperature current = new Temperature(290.93);
        Temperature similar1 = new Temperature(290.931);
        assertEquals("Temperature is compared at two decimal places", current, similar1);
        assertEquals("Temperature is compared at two decimal places", current.hashCode(), similar1.hashCode());

        Temperature similar2 = new Temperature(290.929);
        assertEquals("Temperature is compared at two decimal places", current, similar2);
        assertEquals("Temperature is compared at two decimal places", current.hashCode(), similar2.hashCode());

        Temperature different1 = new Temperature(288.95);
        assertNotEquals("Temperature is compared at two decimal places", different1, current);

        Temperature different2 = new Temperature(288.939);
        assertNotEquals("Temperature is compared at two decimal places", different2, current);
    }

    @Test
    public void testFreezingPoints() {
        Temperature kelvin = new Temperature(273.15);
        Temperature celsius = Temperature.fromCelsius(0);

        assertEquals(kelvin, celsius);
        assertEquals(kelvin.hashCode(), celsius.hashCode());
        
        assertEquals(0.0, kelvin.toCelsius(), EPSILON);
        assertEquals(32.0, kelvin.toFahrenheit(), EPSILON);
    }

    @Test
    public void testBoilingPoints() {
        Temperature kelvin = new Temperature(373.15);
        Temperature celsius = Temperature.fromCelsius(100);

        assertEquals(kelvin, celsius);
        assertEquals(kelvin.hashCode(), celsius.hashCode());
        
        assertEquals(100.0, kelvin.toCelsius(), EPSILON);
        assertEquals(212.0, kelvin.toFahrenheit(), EPSILON);
    }

    @Test
    public void testToString() {
        Temperature freezingPoint = new Temperature(273.15);
        assertEquals("273.15", freezingPoint.toString());

        Temperature absoluteZero = new Temperature(0);
        assertEquals("0", absoluteZero.toString());

        Temperature boilingPoint = new Temperature(373.15);
        assertEquals("373.15", boilingPoint.toString());

        Temperature kelvin = new Temperature(123.456789);
        assertEquals("123.46", kelvin.toString());
    }
}
