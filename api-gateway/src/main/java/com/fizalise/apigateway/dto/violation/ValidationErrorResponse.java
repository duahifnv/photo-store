package com.fizalise.apigateway.dto.violation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
