package com.fizalise.inventoryservice.dto.violation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
