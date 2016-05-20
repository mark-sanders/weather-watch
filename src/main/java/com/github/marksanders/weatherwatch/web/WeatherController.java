package com.github.marksanders.weatherwatch.web;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.marksanders.weatherwatch.weather.Weather;
import com.github.marksanders.weatherwatch.weather.WeatherService;

@Controller
public class WeatherController {
    private static final int LONDON_ID = 2643743;
    private static final int HONG_KONG_ID = 1819729;

    private static final SortedMap<String, Integer> CITIES;
    
    static {
        SortedMap<String, Integer> cities = new TreeMap<>();
        cities.put("London", LONDON_ID);
        cities.put("Hong Kong", HONG_KONG_ID);
        
        CITIES = Collections.unmodifiableSortedMap(cities);
    }

    @Resource
    private WeatherService weatherService;
    
    @RequestMapping(value="/weather", method=RequestMethod.GET)
    public String weatherForm(final Model model) {
        
        model.addAttribute("cities", CITIES);
        WeatherForm weatherForm = new WeatherForm();
        weatherForm.setCityId(LONDON_ID);
        model.addAttribute("weatherForm", weatherForm);
        return "weather";
    }
    
    @RequestMapping(value="/weather", method=RequestMethod.POST)
    public String weatherSubmit(
            @ModelAttribute WeatherForm weatherForm,
            final Model model) {
        model.addAttribute("cities", CITIES);

        if (weatherForm.getCityId() != null) {
            final Weather weather = weatherService.getWeatherForCity(weatherForm.getCityId());
            model.addAttribute("weather", weather);
        }

        return "weather";
    }
}
