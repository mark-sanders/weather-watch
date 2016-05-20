package com.github.marksanders.weatherwatch.service;

import com.github.marksanders.weatherwatch.api.WeatherResultJson;

public interface OpenWeatherMapService {
    WeatherResultJson getWeatherForCity(int cityId);
}
