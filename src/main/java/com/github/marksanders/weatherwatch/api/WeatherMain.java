package com.github.marksanders.weatherwatch.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Main weather details.
 * @author masander
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMain {
    
    private Double temp;

    private Integer pressure;
    
    private Integer humidity;

    @JsonProperty("temp_min")
    private Double tempMin;

    @JsonProperty("temp_max")
    private Double tempMax;

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
