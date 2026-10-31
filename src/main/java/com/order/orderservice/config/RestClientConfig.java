package com.order.orderservice.config;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${payment.client.connect-timeout}")
    private Duration connectTimeout;

    @Value("${payment.client.read-timeout}")
    private Duration readTimeout;

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {

        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);

        return RestClient.builder()
                .requestFactory(factory);
    }
}


