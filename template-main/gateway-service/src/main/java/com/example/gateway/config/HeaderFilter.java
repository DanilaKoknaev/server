package com.example.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class HeaderFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Проверяем наличие заголовка spring-cloud-course
        String headerValue = exchange.getRequest()
                .getHeaders()
                .getFirst("spring-cloud-course");

        if (headerValue == null || headerValue.trim().isEmpty()) {
            // Если заголовка нет - возвращаем 403
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // Продолжаем цепочку фильтров
        return chain.filter(exchange);
    }
}
