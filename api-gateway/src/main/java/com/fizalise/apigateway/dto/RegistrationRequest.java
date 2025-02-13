package com.fizalise.apigateway.dto;

public record RegistrationRequest(String username,
                                  String password,
                                  String email,
                                  String name,
                                  String surname) {}
