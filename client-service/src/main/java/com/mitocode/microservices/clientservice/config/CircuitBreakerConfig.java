package com.mitocode.microservices.clientservice.config;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfig {
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> customDefaultConfig() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
                        .slidingWindowSize(15) //Default 100, ventana para evaluacion
                        .failureRateThreshold(20) //Default 50, porcentaje de error
                        .waitDurationInOpenState(Duration.ofSeconds(20L)) //Default 60, tiempo en el cual se encuentra abierto
                        .permittedNumberOfCallsInHalfOpenState(5) //Default 10 peticiones, evaluaciones en estado abierto
                        //Metricas de llamadas lentas
                        .slowCallDurationThreshold(Duration.ofMillis(750L)) //Demora considerada como error, solo tiempo de descarga de postman
                        .slowCallRateThreshold(50) //porcentaje de error para lentitud
                        .build()).build());
    }
}
