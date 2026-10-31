package com.order.orderservice.service;


import com.order.orderservice.client.PaymentHttpClient;
import com.order.orderservice.dto.PaymentRequest;
import com.order.orderservice.dto.PaymentResponse;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.stereotype.Service;

@Service
public class PaymentRetryService {

    private final PaymentHttpClient paymentHttpClient;

    public PaymentRetryService(PaymentHttpClient paymentHttpClient) {
        this.paymentHttpClient = paymentHttpClient;
    }

    @Retry(name = "paymentService")
    public PaymentResponse processPayment(PaymentRequest request) {

        System.out.println("Retry Service: Calling payment API");

        return paymentHttpClient.makePayment(request);
    }
}
