package com.fizalise.authenticationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(@NotBlank
                                    @Schema(description = "Идентификатор пользователя",
                                            example = "user") String username,
                                    @NotBlank
                                    @Schema(description = "Пароль", minLength = 6,
                                            maxLength = 30, example = "3afl3ajf") String password) {}