/*
 * Author Name: Deepak Vishwkarma
 * Date: 19-02-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.apiGateWay.appconfig;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/user/v1/**")
                        .uri("lb://auth-service:8082/*"))

                .route(p -> p
                        .path("/api/v2/**")
                        .uri("lb://movie-service2:8085/*"))
                .build();
    }
}
