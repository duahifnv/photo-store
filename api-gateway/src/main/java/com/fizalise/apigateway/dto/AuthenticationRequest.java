package com.fizalise.apigateway.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(@NotBlank String username, @NotBlank String password) {}
