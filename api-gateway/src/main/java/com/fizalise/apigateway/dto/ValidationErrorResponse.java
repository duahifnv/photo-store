package com.fizalise.apigateway.dto;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
