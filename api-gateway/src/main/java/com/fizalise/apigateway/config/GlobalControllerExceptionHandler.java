package com.fizalise.apigateway.config;

import com.fizalise.apigateway.dto.ValidationErrorResponse;
import com.fizalise.apigateway.dto.Violation;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    // Обработка исключений, которые так и не были обработаны
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {
        log.error("Unhandled exception [%s]: %s".formatted(e.getClass().getSimpleName(), e.getMessage()));
        return "Сбой на сервере";
    }
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handle(JwtException e) {
        log.info("Jwt exception: " + e.getMessage());
        return e.getMessage();
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handle(ResponseStatusException e) {
        log.info("Response status exception: " + e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
    }
    // Обработка ошибок валидации параметров запроса и переменных пути запроса
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handle(ConstraintViolationException e) {
        List<Violation> violations = e.getConstraintViolations().stream()
                .map(v -> new Violation(
                        v.getPropertyPath().toString(), v.getMessage()
                )).toList();
        return new ValidationErrorResponse(violations);
    }
    // Обработка ошибок полей тела запроса
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handle(MethodArgumentNotValidException e) {
        List<Violation> fieldViolations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(
                        error.getField(), error.getDefaultMessage()
                ))
                .toList();
        return new ValidationErrorResponse(fieldViolations);
    }
}
