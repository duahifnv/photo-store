package com.fizalise.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ClientErrorResponse(String timestamp, int status, String error, String message, @JsonIgnore String path) {
}
