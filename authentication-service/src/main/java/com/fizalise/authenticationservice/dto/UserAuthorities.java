package com.fizalise.authenticationservice.dto;

import java.util.Collection;
import java.util.List;

public record UserAuthorities(String username, Collection<String> authorities) {
}
