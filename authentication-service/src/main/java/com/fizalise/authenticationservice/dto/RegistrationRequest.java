package com.fizalise.authenticationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistrationRequest(@NotBlank String username,
                                  @NotBlank @Length(min = 6, max = 30) String password,
                                  @NotBlank @Email String email,
                                  @NotBlank String name,
                                  @NotBlank String surname) {}