package com.fizalise.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.function.Function;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
@RequiredArgsConstructor
public class RoutesConfig {
    private final Function<ServerRequest, ServerRequest> authenticationFilter;
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return route("inventory-service-route")
                .route(path("/api/v1/inventory/**"), http("http://localhost:8080"))
                .before(authenticationFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return route("order-service-route")
                .route(path("/api/v1/order/**"), http("http://localhost:8081"))
                .before(authenticationFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> authenticationServiceRoute() {
        return route("authentication-service-route")
                .route(path("/api/v1/auth/**"), http("http://localhost:8083"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRouteSwagger() {
        return route("inventory-service-route-swagger")
                .route(path("/aggregate/inventory-service/v1/api-docs"), http("http://localhost:8080"))
                .filter(setPath("/api/v1/api-docs"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceRouteSwagger() {
        return route("order-service-route-swagger")
                .route(path("/aggregate/order-service/v1/api-docs"), http("http://localhost:8081"))
                .filter(setPath("/api/v1/api-docs"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> authenticationServiceRouteSwagger() {
        return route("authentication-service-route-swagger")
                .route(path("/aggregate/auth-service/v1/api-docs"), http("http://localhost:8083"))
                .filter(setPath("/api/v1/api-docs"))
                .build();
    }
}
