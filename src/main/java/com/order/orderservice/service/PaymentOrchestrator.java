package com.order.orderservice.service;



import com.order.orderservice.dto.PaymentRequest;
import com.order.orderservice.dto.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.stereotype.Service;

@Service
public class PaymentOrchestrator {

    private final PaymentRetryService paymentRetryService;

    public PaymentOrchestrator(
            PaymentRetryService paymentRetryService) {

        this.paymentRetryService = paymentRetryService;
    }

    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "paymentFallback"
    )
    public PaymentResponse processPayment(PaymentRequest request) {

        System.out.println("Circuit Breaker: Processing payment");

        return paymentRetryService.processPayment(request);
    }

    // Fallback method
    public PaymentResponse paymentFallback(
            PaymentRequest request,
            Throwable exception) {

        System.out.println("================================");
        System.out.println("FALLBACK EXECUTED");
        System.out.println("Reason: " + exception.getMessage());
        System.out.println("================================");

        return new PaymentResponse(
                request.orderId(),
                "PENDING",
                "Payment service is temporarily unavailable. Please try again later."
        );
    }
}
