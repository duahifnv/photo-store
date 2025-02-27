package com.fizalise.authenticationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserInfo(
        @Schema(description = "Идентификатор пользователя",
                example = "user") String username,
        @Schema(description = "Почта", format = "email@domen.xx",
                example = "mail@mail.ru") String email,
        @Schema(description = "Имя",
                example = "Юзернейм") String name,
        @Schema(description = "Фамилия",
                example = "Юзернеймов") String surname) {
}
