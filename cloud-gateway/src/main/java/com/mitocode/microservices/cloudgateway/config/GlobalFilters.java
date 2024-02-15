package com.mitocode.microservices.cloudgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class GlobalFilters implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //Prefilter
        Long startTime = System.currentTimeMillis();

        Optional<String> appCallerName = Optional.ofNullable(
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("appCallerName")
        );

        if (appCallerName.isEmpty()) {
            exchange.getRequest().mutate().header("appCallerName", "CloudGateway").build();
        }

        log.info(exchange.getRequest().toString());

        return chain.filter(exchange).then(Mono.fromRunnable(
                () -> {
                    //Postfilter
                    log.info("[Mitocode][GlobalFilters]: Time elapse: " + (System.currentTimeMillis() - startTime));
                    exchange.getResponse().getHeaders().add("token", exchange.getRequest()
                            .getHeaders()
                            .getFirst("appCallerName"));
                    exchange.getResponse().getCookies()
                            .add("Token", ResponseCookie.from("MitocodeToken", exchange.getRequest()
                                    .getHeaders()
                                    .getFirst("appCallerName")).build());
                }
        ));
    }
}
