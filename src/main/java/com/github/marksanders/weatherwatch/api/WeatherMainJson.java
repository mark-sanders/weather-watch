package com.github.marksanders.weatherwatch.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Main weather details.
 * @author masander
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMainJson {
    
    private Double temp;

    private Integer pressure;
    
    private Integer humidity;

    @JsonProperty("temp_min")
    private Double tempMin;

    @JsonProperty("temp_max")
    private Double tempMax;

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
            final Double temp, 
            final Integer pressure, 
            final Integer humidity, 
            final Double tempMin, 
            final Double tempMax) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }



    /**
     * Get the temperature in degrees Kelvin
     * @return the temperature
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * Get the atmospheric pressure in hPa
     * @return the pressure
     */
    public Integer getPressure() {
        return pressure;
    }

    /**
     * Get the percentage humidity
     * @return the humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * Get the minimum temperature in degrees Kelvin
     * @return the minimum temperature
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * Get the minimum temperature in degrees Kelvin
     * @return the minimum temperature
     */
    public Double getTempMax() {
        return tempMax;
    }
}
