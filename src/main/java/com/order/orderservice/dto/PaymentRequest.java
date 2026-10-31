package com.order.orderservice.dto;
import java.math.BigDecimal;

public record PaymentRequest(
        Long orderId,
        BigDecimal amount,
        String testMode,
        long delayMs
) {
}
