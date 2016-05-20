package com.github.marksanders.weatherwatch.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

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
public class WeatherResult {

    private String name;
    
    private int id;
    
    @JsonProperty("dt")
    private long timestamp;

    @JsonProperty("weather")
    private List<Weather> weatherList = new ArrayList<>();
    
    @JsonProperty("sys")
    private WeatherDetails details;

    private WeatherMain main;
    
    /**
     * Default constructor for weather result. 
     */
    public WeatherResult() {
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
    public WeatherResult(
            final String name, 
            final int id, 
            final long timestamp, 
            final List<Weather> weatherList, 
            final WeatherDetails details,
            final WeatherMain main) {
        this.name = name;
        this.id = id;
        this.timestamp = timestamp;
        this.weatherList = weatherList;
        this.details = details;
        this.main = main;
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
    public long getTimestamp() {
        return timestamp;
    }
    
    /** 
     * Get the list of {@link Weather} items
     * @return the list of weather items
     */
    public List<Weather> getWeatherList() {
        return weatherList;
    }

    /**
     * Convenience method to return the expected single {@link Weather} item
     * @return the weather item or null if this list is empty 
     */
    public Weather getWeather() {
        if (CollectionUtils.isEmpty(this.weatherList)) {
            return null; 
        } else {
            return this.weatherList.get(0);
        }
    }

    /**
     * Get the internal details of the weather result. 
     * @return the details
     */
    public WeatherDetails getDetails() {
        return details;
    }

    /**
     * Get the main weather details.
     * @return
     */
    public WeatherMain getMain() {
        return main;
    }
}
