package com.dp.scm.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/user/**")
                        .uri("lb://user-service")
                        .id("user-ms"))

                .route(r -> r.path("/api/document/**")
                        .uri("lb://document-service")
                        .id("doc-ms"))
                .build();
    }

}
