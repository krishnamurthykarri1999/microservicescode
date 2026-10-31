package com.order.orderservice.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        Long productId,
        String productName,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal totalAmount,
        String status,
        LocalDateTime createdAt
) {
}
