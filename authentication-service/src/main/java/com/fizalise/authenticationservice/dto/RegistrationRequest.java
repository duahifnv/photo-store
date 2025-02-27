package com.fizalise.authenticationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistrationRequest(@NotBlank
                                  @Schema(description = "Идентификатор пользователя",
                                          example = "user") String username,
                                  @NotBlank @Length(min = 6, max = 30)
                                  @Schema(description = "Пароль", example = "3afl3ajf") String password,
                                  @NotBlank @Email
                                  @Schema(description = "Почта", format = "email@domen.xx",
                                          example = "mail@mail.ru") String email,
                                  @NotBlank
                                  @Schema(description = "Имя",
                                          example = "Юзернейм") String name,
                                  @NotBlank
                                  @Schema(description = "Фамилия",
                                          example = "Юзернеймов") String surname) {}