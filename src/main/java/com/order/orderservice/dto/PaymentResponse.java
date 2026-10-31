package com.order.orderservice.dto;


public record PaymentResponse(
        Long orderId,
        String status,
        String message
) {
}
