package com.fizalise.apigateway.config;

import com.fizalise.apigateway.client.AuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
//    private final ObservationRegistry observationRegistry;
    @Value("${client.authentication-service.url}")
    private String authenticationServiceUrl;
    @Bean
    public AuthClient authenticationClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(authenticationServiceUrl)
                .build();
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(AuthClient.class);
    }
}
