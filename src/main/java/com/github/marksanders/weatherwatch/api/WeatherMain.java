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
     * Get the temperature in Kelvin
     * @return the temperature
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * Set the temperature in Kelvin
     * @param temp the new temperature
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     * Get the atmospheric pressure in hPa
     * @return the pressure
     */
    public Integer getPressure() {
        return pressure;
    }

    /**
     * Set the atmospheric pressure in hPa
     * @param pressure the new pressure
     */
    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    /**
     * Get the percentage humidity
     * @return the humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * Set the percentage humidity
     * @param humidity the new humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * Get the minimum temperature in Kelvin
     * @return the minimum temperature
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * Set the minimum temperature in Kelvin
     * @param temp the new minimum temperature
     */
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * Get the minimum temperature in Kelvin
     * @return the minimum temperature
     */
    public Double getTempMax() {
        return tempMax;
    }

    /**
     * Set the maximum temperature in Kelvin
     * @param temp the new maximum temperature
     */
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

}
