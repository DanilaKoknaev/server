package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("client-service", r -> r
                        .path("/api/client/**")
                        .filters(f -> f
                                .filter(headerFilter())
                                .removeRequestHeader("spring-cloud-course")
                        )
                        .uri("lb://CLIENT-SERVICE"))
                .route("book-service", r -> r
                        .path("/api/books/**")
                        .filters(f -> f
                                .filter(headerFilter())
                                .removeRequestHeader("spring-cloud-course")
                        )
                        .uri("lb://BOOK-SERVICE"))
                .build();
    }

    @Bean
    public HeaderFilter headerFilter() {
        return new HeaderFilter();
    }
}
