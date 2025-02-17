package com.fizalise.apigateway.config;

import com.fizalise.apigateway.client.AuthClient;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AuthenticationConfig {
    private static final String BEARER_PREFIX = "Bearer ";
    private final HandlerExceptionResolver exceptionResolver;
    private final AuthClient authClient;
    @Autowired
    public AuthenticationConfig(AuthClient authClient,
                                   @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.authClient = authClient;
        this.exceptionResolver = exceptionResolver;
    }
    @Bean
    public Function<ServerRequest, ServerRequest> authenticationFilter() {
        return request -> {
            HttpServletRequest servletRequest = request.servletRequest();
            String authHeader = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                return ServerRequest.from(request)
                        .build();
            }
            try {
                String jwt = authHeader.substring(BEARER_PREFIX.length());
                var userAuthorities = authClient.getAuthorities(jwt);
                return ServerRequest.from(request)
                        .header("username", userAuthorities.username())
                        .header("authorities",
                                String.join(",", userAuthorities.authorities())
                        )
                        .build();
            } catch (JwtException e) {
                exceptionResolver.resolveException(servletRequest, null, null, e);
                return ServerRequest.from(request)
                        .build();
            }
        };
    }
}
