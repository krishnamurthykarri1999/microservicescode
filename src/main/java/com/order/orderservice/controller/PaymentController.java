package com.order.orderservice.controller;

import com.order.orderservice.service.PaymentService_oldfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class PaymentController {

    @Autowired
    private PaymentService_oldfile paymentServiceOldfile;

    @GetMapping("processPayment")
    public String processPayment() {
        return paymentServiceOldfile.processPayment();
    }
}
