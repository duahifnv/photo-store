package com.fizalise.orderservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizalise.orderservice.dto.ClientErrorResponse;
import com.fizalise.orderservice.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ClientErrorResponse handle(HttpClientErrorException e) {
        try {
            String responseBody = e.getResponseBodyAsString();
            log.error("Выброшено исключение от клиента: {}", responseBody);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBody, ClientErrorResponse.class);
        }
        catch (JsonProcessingException jpe) {
            log.error("Ошибка JSON-парсинга: {}", jpe.getMessage());
            throw new ServerException();
        }
    }
}
