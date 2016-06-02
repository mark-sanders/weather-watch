package com.github.marksanders.weatherwatch.weather;

import java.text.DecimalFormat;

public class Temperature {

    private static final String TEMPERATURE_FORMAT_PATTERN = "#.##";

    private static final ThreadLocal<DecimalFormat> decimalFormat = 
            new ThreadLocal<DecimalFormat>() {
                protected DecimalFormat initialValue() {
                    return new DecimalFormat(TEMPERATURE_FORMAT_PATTERN);
                };
            };
    
    private static final double CELSIUS_OFFSET = 273.15;
    private static final double FAHRENHEIT_SCALE = 9.0 / 5.0;
    private static final double FAHRENHEIT_OFFSET = 32.0;
    
    private final double kelvin;

    public static Temperature fromCelsius(double temperatureCelsius) {
        return new Temperature(temperatureCelsius + CELSIUS_OFFSET);
    }

    public Temperature(final double kelvin) {
        this.kelvin = kelvin;
    }

    public int getCelsius() {
        return (int) (kelvin - CELSIUS_OFFSET);
    }
    
    public int getFahrenheit() {
        return (int) (((kelvin - CELSIUS_OFFSET) * FAHRENHEIT_SCALE) + FAHRENHEIT_OFFSET);
    }
    
    @Override
    public String toString() {
        return decimalFormat.get().format(kelvin);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Temperature)) {
            return false;
        }
        
        Temperature other = (Temperature) object;
        return toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
