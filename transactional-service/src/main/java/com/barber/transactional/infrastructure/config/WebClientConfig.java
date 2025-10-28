package com.barber.transactional.infrastructure.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private String BASE_EMPLOYED;
    private String BASE_PRODUCT;

    @LoadBalanced
    @Bean
    public WebClient.Builder employedWebClient(){
        return WebClient.builder().baseUrl(BASE_EMPLOYED);

    }

    @LoadBalanced
    @Bean
    public WebClient.Builder productWebClient(){
        return WebClient.builder().baseUrl(BASE_PRODUCT);

    }
}
