package com.github.marksanders.weatherwatch.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Details of the weather result
 * @author masander
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherSys {
    // ignoring type
    // ignoring id
    // ignoring message
    private String country;
    private long sunrise;
    private long sunset;

    /**
     * Get the country code (GB, JP etc.) for the city
     * @return the country code
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Set the country code (GB, JP etc.)
     * @param country the new country code
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the sunrise timestamp for the city in Unix UTC format
     * @return the sunrise timestamp
     */
    public long getSunrise() {
        return sunrise;
    }

    /**
     * Set the sunrise timestamp for the city 
     * @param sunrise the new sunrise timestamp
     */
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Get the sunset timestamp for the city in Unix UTC format
     * @return the sunset timestamp
     */
    public long getSunset() {
        return sunset;
    }

    /**
     * Set the sunset timestamp for the city 
     * @param sunset the new sunset timestamp
     */
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
