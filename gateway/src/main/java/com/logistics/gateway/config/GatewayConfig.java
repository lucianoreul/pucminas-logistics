package com.logistics.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter filter;

    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER"))

                .route("auth", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTH"))
                .build();
    }
}
