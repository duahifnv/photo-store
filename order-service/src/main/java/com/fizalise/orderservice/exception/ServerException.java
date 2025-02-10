package com.fizalise.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServerException extends ResponseStatusException {
    public ServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Неполадки на сервере");
    }
    public ServerException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
