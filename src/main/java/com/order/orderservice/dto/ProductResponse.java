package com.order.orderservice.dto;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ProductResponse(
        Long id,
        String sku,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}



