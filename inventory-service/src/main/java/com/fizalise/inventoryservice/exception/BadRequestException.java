package com.fizalise.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, "Bad request");
    }
    public BadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
