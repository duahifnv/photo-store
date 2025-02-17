package com.fizalise.authenticationservice.dto;

import java.util.Collection;

public record UserAuthorities(String username, Collection<String> authorities) {
}
