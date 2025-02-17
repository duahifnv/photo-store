package com.fizalise.orderservice.client;

import com.fizalise.orderservice.client.dto.UserInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface AuthClient {
    @GetExchange("/api/v1/auth/users/{username}")
    UserInfo getUserInfo(@PathVariable String username);
}
