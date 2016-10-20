package com.github.marksanders.weatherwatch.service;

import java.util.Optional;

import com.github.marksanders.weatherwatch.api.WeatherResultJson;

public interface OpenWeatherMapService {
    Optional<WeatherResultJson> getWeatherForCity(int cityId);
}
