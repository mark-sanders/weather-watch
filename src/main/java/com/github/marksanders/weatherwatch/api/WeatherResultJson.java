package com.github.marksanders.weatherwatch.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO representing the result from the OpenWeatherMap.org JSON API.
 *  
 * See http://openweathermap.org/current#parameter
 * 
 * @author masander
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResultJson {

    private String name;
    
    private int id;
    
    private long dt;

    @JsonProperty("weather")
    private List<WeatherJson> weatherList = new ArrayList<>();
    
    @JsonProperty("sys")
    private Optional<WeatherDetailsJson> details;

    private Optional<WeatherMainJson> main;
    
    /**
     * Default constructor for weather result. 
     */
    public WeatherResultJson() {
        // empty default constructor
    }
    
    /**
     * Constructor for testing the weather result.
     * @param name the city name
     * @param id the city id
     * @param timestamp the timestamp in Unix UTC format
     * @param weatherList the list of weather items
     * @param details
     * @param main
     */
    public WeatherResultJson(
            final String name, 
            final int id, 
            final long dt, 
            final List<WeatherJson> weatherList, 
            final WeatherDetailsJson details,
            final WeatherMainJson main) {
        this.name = name;
        this.id = id;
        this.dt = dt;
        if (weatherList != null) {
            this.weatherList = weatherList;
        }
        
        this.details = Optional.ofNullable(details);
        this.main = Optional.ofNullable(main);
    }
    

    /**
     * Get the city name
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the city ID 
     * @return the city ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the timestamp in Unix UTC format
     * @return the timestamp
     */
    public long getDt() {
        return dt;
    }
    
    /** 
     * Get the list of {@link WeatherJson} items
     * @return the list of weather items
     */
    public List<WeatherJson> getWeatherList() {
        return weatherList;
    }

    /**
     * Convenience method to return the expected single {@link WeatherJson} item
     * @return the weather item or null if this list is empty 
     */
    public Optional<WeatherJson> getWeather() {
        return weatherList.stream().findFirst();
    }

    /**
     * Get the internal details of the weather result. 
     * @return the details
     */
    public Optional<WeatherDetailsJson> getDetails() {
        return details;
    }

    /**
     * Get the main weather details.
     * @return
     */
    public Optional<WeatherMainJson> getMain() {
        return main;
    }
}
