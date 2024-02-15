package com.mitocode.microservices.cloudgateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.CustomConfiguration> {

    public CustomFilter() {
        super(CustomConfiguration.class);
    }

    //Nombre del filtro
    @Override
    public String name() {
        return "MitocodeFilter";
    }

    //Orden de los parametros para el filtro
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("headerKey","headerValue");
    }

    @Override
    public GatewayFilter apply(CustomConfiguration config) {
        return (exchange, chain) -> {
            //Pre filter
            log.info("[CustomFilter][apply]: Pre filter");
            exchange.getRequest().mutate().header("appCallerName2", "CustomFilter");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //Post filter
                log.info("[CustomFilter][apply]: Post filter");
                exchange.getResponse().getHeaders().add(config.headerKey, config.headerValue);
            }));
        };
    }

    @Data
    public static class CustomConfiguration {
        private String headerKey;
        private String headerValue;
    }
}

