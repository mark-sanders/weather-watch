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
    
    private WeatherSys sys;

    private WeatherMain main;

    /**
     * Get the city name
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the city name
     * @param name the new city name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the city ID 
     * @return the city ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Set the city ID
     * @param id the new city ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the timestamp in in Unix UTC format
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    
    /**
     * Set the timestamp in in Unix UTC format
     * @param timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /** 
     * Get the list of {@link Weather} items
     * @return the list of weather items
     */
    public List<Weather> getWeatherList() {
        return weatherList;
    }
    
    /**
     * Get the list of {@link Weather} items
     * @param weatherList the list of weather items
     */
    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
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
     * 
     * @return
     */
    public WeatherSys getSys() {
        return sys;
    }
    
    public void setSys(WeatherSys sys) {
        this.sys = sys;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }
}
