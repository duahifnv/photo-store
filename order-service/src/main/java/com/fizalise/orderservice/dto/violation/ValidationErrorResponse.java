package com.fizalise.orderservice.dto.violation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
