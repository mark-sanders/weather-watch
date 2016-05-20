package com.github.marksanders.weatherwatch.weather;

import java.math.BigDecimal;
import java.math.MathContext;

public class Temperature {
    
    private static final MathContext MC = new MathContext(5);
    private static final BigDecimal CELSIUS_OFFSET = new BigDecimal(273.15, MC);
    private static final BigDecimal FAHRENHEIT_SCALE = new BigDecimal(9.0 / 5.0, MC);
    private static final BigDecimal FAHRENHEIT_OFFSET = new BigDecimal(459.67, MC);
    
    private final BigDecimal kelvin;

    public static Temperature fromCelsius(double temperatureCelsius) {
        BigDecimal celsius = new BigDecimal(temperatureCelsius, MC);
        return new Temperature(celsius.add(CELSIUS_OFFSET, MC));
    }

    public Temperature(final double kelvin) {
        this.kelvin = new BigDecimal(kelvin, MC);
    }
    
    private Temperature(final BigDecimal kelvin) {
        this.kelvin = kelvin;
    }

    public int getCelsius() {
        return kelvin.subtract(CELSIUS_OFFSET, MC).intValue();
    }
    
    public int getFahrenheit() {
        return kelvin.multiply(FAHRENHEIT_SCALE, MC).subtract(FAHRENHEIT_OFFSET, MC).intValue();
    }
    
    @Override
    public String toString() {
        return kelvin.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Temperature)) {
            return false;
        }
        
        Temperature other = (Temperature) object;
        return 0 == kelvin.compareTo(other.kelvin);
    }

    @Override
    public int hashCode() {
        return kelvin.hashCode();
    }
}
