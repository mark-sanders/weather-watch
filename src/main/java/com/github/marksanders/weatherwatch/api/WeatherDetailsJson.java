package com.github.marksanders.weatherwatch.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Details of the weather result.
 * @author masander
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetailsJson {
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
     * Get the sunrise timestamp for the city in Unix UTC format
     * @return the sunrise timestamp
     */
    public long getSunrise() {
        return sunrise;
    }

    /**
     * Get the sunset timestamp for the city in Unix UTC format
     * @return the sunset timestamp
     */
    public long getSunset() {
        return sunset;
    }
}
