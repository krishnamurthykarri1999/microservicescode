package com.payment.payment.dto;


public record PaymentResponse(
        Long orderId,
        String status,
        String message
) {
}
