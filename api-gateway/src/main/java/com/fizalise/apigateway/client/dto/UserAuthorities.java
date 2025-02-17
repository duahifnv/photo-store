package com.fizalise.apigateway.client.dto;

import java.util.Collection;

public record UserAuthorities(String username, Collection<String> authorities) {
}
