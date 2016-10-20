package com.github.marksanders.weatherwatch.api;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Main weather details.
 * @author masander
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMainJson {
    
    @JsonProperty("temp")
    private Optional<Double> temp;

    private Optional<Integer> pressure;
    
    private Optional<Integer> humidity;

    @JsonProperty("temp_min")
    private Optional<Double> tempMin;

    @JsonProperty("temp_max")
    private Optional<Double> tempMax;

    /**
     * Default constructor for the main weather details (for JSON). 
     */
    public WeatherMainJson() {
    }
    
    /**
     * Create the main weather details (for testing).
     * @param temp the temperature in Kelvin
     * @param pressure atmospheric pressure in hPa
     * @param humidity the percentage humidity
     * @param tempMin the mininum temperature in Kelvin
     * @param tempMax the maxinum temperature in Kelvin
     */
    public WeatherMainJson(
            final double temp, 
            final int pressure, 
            final int humidity, 
            final double tempMin, 
            final double tempMax) {
        this.temp = Optional.of(temp);
        this.pressure = Optional.of(pressure);
        this.humidity = Optional.of(humidity);
        this.tempMin = Optional.of(tempMin);
        this.tempMax = Optional.of(tempMax);
    }



    /**
     * Get the temperature in degrees Kelvin
     * @return the temperature
     */
    public Optional<Double> getTemp() {
        return temp;
    }

    /**
     * Get the atmospheric pressure in hPa
     * @return the pressure
     */
    public Optional<Integer> getPressure() {
        return pressure;
    }

    /**
     * Get the percentage humidity
     * @return the humidity
     */
    public Optional<Integer> getHumidity() {
        return humidity;
    }

    /**
     * Get the minimum temperature in degrees Kelvin
     * @return the minimum temperature
     */
    public Optional<Double> getTempMin() {
        return tempMin;
    }

    /**
     * Get the minimum temperature in degrees Kelvin
     * @return the minimum temperature
     */
    public Optional<Double> getTempMax() {
        return tempMax;
    }
}
