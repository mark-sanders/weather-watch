package com.github.marksanders.weatherwatch;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@PropertySources({
    @PropertySource(value="file:${user.home}/.weatherwatch/weatherwatch.properties", ignoreResourceNotFound=true),
    @PropertySource(value="classpath:weatherwatch.properties", ignoreResourceNotFound=true)
})
public class WeatherwatchConfiguration {
    
    @Value("${weatherwatch.url}")
    private String weatherwatchUrl;

    @Value("${weatherwatch.api.key}")
    private String apiKey;

    @Value("${weatherwatch.http.proxyHost:null}")
    private String proxyHost;
    
    @Value("${weatherwatch.http.proxyPort:0}")
    private int proxyPort;
    
    @Bean
    public ClientHttpRequestFactory requestFactory() {
        final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        if (proxyHost != null) {
            final SocketAddress socketAddress = new InetSocketAddress(proxyHost, proxyPort);
            requestFactory.setProxy(new Proxy(Type.HTTP, socketAddress));
        }

        return requestFactory;
    }

    @Bean
    @Scope("prototype")
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory());
        return restTemplate;
    }

    @Bean
    @Scope("prototype")
    public UriComponentsBuilder builder() {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherwatchUrl);
        builder.queryParam("appid", apiKey);

        return builder;
    }

}
