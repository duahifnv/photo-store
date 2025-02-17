package com.fizalise.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.function.Function;

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
                /* Любой запрос, путь которого имеет паттерн /api/v1/inventory/** будет перенаправлен
                   на порт 8080 и хост localhost */
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

}
