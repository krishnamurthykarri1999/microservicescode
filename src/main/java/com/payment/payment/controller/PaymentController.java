package com.payment.payment.controller;
import com.payment.payment.dto.PaymentRequest;
import com.payment.payment.dto.PaymentResponse;
import com.payment.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse makePayment(
            @RequestBody PaymentRequest request) {

        System.out.println("================================");
        System.out.println("Payment API called");
        System.out.println("Order ID: " + request.orderId());
        System.out.println("Test Mode: " + request.testMode());

        return paymentService.processPayment(request);
    }
}
