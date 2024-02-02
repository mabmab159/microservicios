package com.mitocode.microservices.clientserviceribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
public class RibbonConfig {

    private final IClientConfig config;

   /* public RibbonConfig(IClientConfig config) {
        this.config = config;
    }*/

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}
