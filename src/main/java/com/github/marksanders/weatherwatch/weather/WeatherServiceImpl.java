package com.github.marksanders.weatherwatch.weather;

import java.time.ZoneId;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.marksanders.weatherwatch.api.WeatherDetailsJson;
import com.github.marksanders.weatherwatch.api.WeatherJson;
import com.github.marksanders.weatherwatch.api.WeatherMainJson;
import com.github.marksanders.weatherwatch.api.WeatherResultJson;
import com.github.marksanders.weatherwatch.service.OpenWeatherMapService;
import com.github.marksanders.weatherwatch.service.TimeZoneService;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Resource
    private TimeZoneService timeZoneService;

    @Resource
    private OpenWeatherMapService openWeatherMap;  
    
    public TimeZoneService getTimeZoneService() {
        return timeZoneService;
    }
    
    public void setOpenWeatherMap(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMap = openWeatherMapService;
    }
    
    public OpenWeatherMapService getOpenWeatherMap() {
        return openWeatherMap;
    }
    
    public void setTimeZoneService(TimeZoneService timeZoneService) {
        this.timeZoneService = timeZoneService;
    }

    @Cacheable("weatherForCity")
    @Override
    public Weather getWeatherForCity(int cityId) {
        final ZoneId timeZone = timeZoneService.getTimeZoneForCity(cityId);
        if (timeZone == null) {
            throw new IllegalStateException("Failed to find a time zone for a city [" + cityId + "]");
        }

        final WeatherResultJson weatherResultJson = openWeatherMap.getWeatherForCity(cityId);
        if (weatherResultJson == null) {
            throw new IllegalStateException("Failed to get result for a city [" + cityId + "]");
        }
        
        final WeatherJson weatherJson = weatherResultJson.getWeather();
        if (weatherJson == null) {
            throw new IllegalStateException("Failed to get weather for a city [" + cityId + "]");
        }

        final WeatherDetailsJson detailsJson = weatherResultJson.getDetails();
        if (detailsJson == null) {
            throw new IllegalStateException("Failed to get weather details for a city [" + cityId + "]");
        }

        final WeatherMainJson mainJson = weatherResultJson.getMain();
        if (mainJson == null) {
            throw new IllegalStateException("Failed to get main weather for a city [" + cityId + "]");
        }

        return new Weather.Builder()
                .cityId(cityId)
                .cityName(weatherResultJson.getName())
                .temperatureKelvin(mainJson.getTemp())
                .description(weatherJson.getDescription())
                .timeZone(timeZone)
                .cityDateTime(weatherResultJson.getDt())
                .citySunriseDateTime(detailsJson.getSunrise())
                .citySunsetDateTime(detailsJson.getSunset())
                .build();
    }

    @Override
    public int getTemperatureForCity(int cityId) {
        final WeatherResultJson weatherResultJson = openWeatherMap.getWeatherForCity(cityId);
        if (weatherResultJson != null) {
            final WeatherJson weatherJson = weatherResultJson.getWeather();
            if (weatherJson != null) {
                final WeatherDetailsJson detailsJson = weatherResultJson.getDetails();
                if (detailsJson != null) {
                    final WeatherMainJson mainJson = weatherResultJson.getMain();
                    if (mainJson != null) {
                        Double tempKelvin = mainJson.getTemp();
                        if (tempKelvin != null) {
                            return new Temperature(tempKelvin).getCelsius();
                        }
                    }
                }
            }
        }

        throw new IllegalStateException("Failed to get temperature for a city");
    }
}
