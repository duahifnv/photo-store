package com.fizalise.apigateway.client;

import com.fizalise.apigateway.client.dto.UserAuthorities;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface AuthClient {
    @GetExchange("/api/v1/auth/authorities")
    UserAuthorities getAuthorities(@RequestParam String jwt);
}
