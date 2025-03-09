package com.fizalise.inventoryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI inventoryServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("API для сервиса учета товаров")
                        .version("1.0.0"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Имя пользователя", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .name("username")
                                .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("Полномочия в формате ROLE_*", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .name("authorities")
                                .in(SecurityScheme.In.HEADER)))
                .addSecurityItem(new SecurityRequirement()
                        .addList("Имя пользователя")
                        .addList("Полномочия в формате ROLE_*"));
    }
}
