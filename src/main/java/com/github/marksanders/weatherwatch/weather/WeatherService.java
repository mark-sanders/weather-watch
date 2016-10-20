package com.github.marksanders.weatherwatch.weather;

public interface WeatherService {
    Weather getWeatherForCity(int cityId);

    int getTemperatureForCity(int cityId);
}
