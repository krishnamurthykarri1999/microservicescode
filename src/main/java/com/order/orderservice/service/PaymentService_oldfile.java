package com.order.orderservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class PaymentService_oldfile {

    @Retry(name = "paymentService")
    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "paymentFallback"
    )
    public String processPayment() {

        System.out.println("Calling Payment Service...");

        // Simulate a failed service call
        throw new RuntimeException("Payment Service is down");
    }

    public String paymentFallback(Throwable ex) {

        System.out.println("Fallback executed");

        return "Payment Service is temporarily unavailable";
    }
}
