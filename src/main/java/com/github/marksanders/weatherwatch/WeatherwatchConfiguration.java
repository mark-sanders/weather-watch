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
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
@PropertySources({
    @PropertySource(value="file:${user.home}/.weatherwatch/weatherwatch.properties", ignoreResourceNotFound=true),
    @PropertySource(value="classpath:weatherwatch.properties", ignoreResourceNotFound=true)
})
public class WeatherwatchConfiguration {
    
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
}
