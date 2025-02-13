package com.fizalise.apigateway.config;

import com.fizalise.apigateway.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final JwtService jwtService;
    private final HandlerExceptionResolver exceptionResolver;
    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService,
                                   @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtService = jwtService;
        this.exceptionResolver = exceptionResolver;
    }
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(AUTH_HEADER_NAME);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(BEARER_PREFIX.length());
        try {
            String username = jwtService.extractUsername(jwt);
            // Если пользователь из токена существует и в контексте его нет
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Собираем токен, который кладется в контекст
                UsernamePasswordAuthenticationToken userPassToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                jwtService.extractRoles(jwt)
                                        .stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .toList()
                        );
                SecurityContextHolder.getContext().setAuthentication(userPassToken);
            }
            filterChain.doFilter(request, response);
        }
        catch (JwtException e) {
            exceptionResolver.resolveException(request, response, null, e);
        }
    }
}
