package com.fizalise.apigateway.client;

import java.util.Collection;

public record UserAuthorities(String username, Collection<String> authorities) {
}
