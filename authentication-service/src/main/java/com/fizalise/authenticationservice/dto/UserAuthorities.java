package com.fizalise.authenticationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;

public record UserAuthorities(
        @Schema(description = "Идентификатор пользователя", example = "user")
        String username,
        @Schema(description = "Список полномочий", example = "[ROLE_USER, ROLE_ADMIN]")
        Collection<String> authorities) {
}
