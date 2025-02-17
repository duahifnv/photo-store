package com.fizalise.orderservice.config;

import com.fizalise.orderservice.client.AuthClient;
import com.fizalise.orderservice.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
//    private final ObservationRegistry observationRegistry;
    @Value("${client.inventory-service.url}")
    private String inventoryServiceUrl;
    @Value("${client.authentication-service.url}")
    private String authenticationServiceUrl;
    @Bean
    public InventoryClient inventoryClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .defaultHeaders(
                        headers -> {
                            headers.set("username", "rest-client");
                            headers.set("authorities", "ROLE_ADMIN");
                        }
                )
                .build();
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(InventoryClient.class);
    }
    @Bean
    public AuthClient authClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(authenticationServiceUrl)
                .defaultHeaders(
                        headers -> {
                            headers.set("username", "rest-client");
                            headers.set("authorities", "ROLE_ADMIN");
                        }
                )
                .build();
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(AuthClient.class);
    }
}
