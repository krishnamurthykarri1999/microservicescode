package com.payment.payment.service;

import com.payment.payment.dto.PaymentRequest;
import com.payment.payment.dto.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentService {

    public PaymentResponse processPayment(PaymentRequest request) {

        String mode = request.testMode() == null
                ? "SUCCESS"
                : request.testMode().toUpperCase();

        // Scenario 1: Simulate payment failure
        if ("FAIL".equals(mode)) {

            System.out.println("Payment Service: Simulating failure");

            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Payment service temporarily unavailable"
            );
        }

        // Scenario 2: Simulate slow response
        if ("SLOW".equals(mode)) {

            System.out.println("Payment Service: Simulating delay");

            try {
                Thread.sleep(request.delayMs());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Payment processing interrupted"
                );
            }
        }

        // Scenario 3: Successful payment
        System.out.println("Payment Service: Payment successful");

        return new PaymentResponse(
                request.orderId(),
                "SUCCESS",
                "Payment processed successfully"
        );
    }
}
