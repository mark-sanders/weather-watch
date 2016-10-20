package com.github.marksanders.weatherwatch.service;

import java.net.URI;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.marksanders.weatherwatch.api.WeatherResultJson;

@Service
public class OpenWeatherMapServiceImpl implements OpenWeatherMapService {
    
    private static final String APPID_PARAM = "appid";

    private static final String CITY_ID_PARAM = "id";

    @Value("${weatherwatch.url}")
    private String weatherwatchUrl;

    @Value("${weatherwatch.api.key}")
    private String apiKey;
    
    @Resource
    private ClientHttpRequestFactory requestFactory;
    
    public String getWeatherwatchUrl() {
        return weatherwatchUrl;
    }

    public void setWeatherwatchUrl(String weatherwatchUrl) {
        this.weatherwatchUrl = weatherwatchUrl;
    }
    
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public ClientHttpRequestFactory getRequestFactory() {
        return requestFactory;
    }

    public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public RestTemplate createRestTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
    
    public UriComponentsBuilder createUriComponentsBuilder() {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherwatchUrl);
        builder.queryParam(APPID_PARAM, apiKey);
        return builder;
    }

    @Override
    public Optional<WeatherResultJson> getWeatherForCity(int cityId) {
        
        final UriComponentsBuilder builder = createUriComponentsBuilder();
        builder.queryParam(CITY_ID_PARAM, cityId);
        final URI uri = builder.build().toUri();
        
        final RestTemplate restTemplate = createRestTemplate();
        final WeatherResultJson weatherResult = restTemplate.getForObject(uri, WeatherResultJson.class);
        return Optional.ofNullable(weatherResult);
    }

}
