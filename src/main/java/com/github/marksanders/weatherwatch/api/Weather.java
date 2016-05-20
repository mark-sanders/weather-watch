package com.github.marksanders.weatherwatch.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Top level description of the weather.
 * @author masander
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

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
     * Set the group of weather parameters
     * @param main the new group of weather parameters
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * Get the description of the weather
     * @return the weather description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the weather
     * @param description the new weather description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the icon code 
     * @return the icon code
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Set the icon code 
     * @param icon the new icon code
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
