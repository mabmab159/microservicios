package com.mitocode.microservices.clientserviceribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //Capacidad para reconocer las diferentes instancias de balanceo
    public RestTemplate configRestTemplate() {
        return new RestTemplate();
    }
}
