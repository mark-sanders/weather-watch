package com.github.marksanders.weatherwatch.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Top level description of the weather.
 * @author masander
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherJson {

    private String main;
    private String description;
    private String icon;

    /**
     * Get group of weather parameters (Rain, Snow, Extreme etc)
     * @return the group of weather parameters
     */
    public String getMain() {
        return main;
    }

    /**
     * Get the description of the weather
     * @return the weather description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the icon code 
     * @return the icon code
     */
    public String getIcon() {
        return icon;
    }
}
