package com.github.marksanders.weatherwatch.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResult {

    private String name;
    
    @JsonProperty("dt")
    private long timestamp;

    @JsonProperty("weather")
    private List<Weather> weatherList = new ArrayList<>();
    
    private WeatherSys sys;

    private WeatherMain main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }
    
    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Weather getWeather() {
        if (CollectionUtils.isEmpty(this.weatherList)) {
            return null; 
        } else {
            return this.weatherList.get(0);
        }
    }

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
