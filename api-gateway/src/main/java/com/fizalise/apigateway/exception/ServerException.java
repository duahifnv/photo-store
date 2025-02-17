package com.fizalise.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServerException extends ResponseStatusException {
    public ServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Неполадки на сервере");
    }
}
