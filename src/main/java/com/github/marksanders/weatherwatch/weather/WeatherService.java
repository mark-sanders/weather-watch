package com.github.marksanders.weatherwatch.weather;

import java.util.Optional;

public interface WeatherService {
    Weather getWeatherForCity(int cityId);

    Optional<Integer> getTemperatureForCity(int cityId);
}
