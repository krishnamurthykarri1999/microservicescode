package com.order.orderservice.controller;
import com.order.orderservice.dto.CreateOrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.dto.PaymentRequest;
import com.order.orderservice.dto.PaymentResponse;
import com.order.orderservice.service.OrderService;
import com.order.orderservice.service.PaymentOrchestrator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final PaymentOrchestrator paymentOrchestrator;

    public OrderController(OrderService orderService,PaymentOrchestrator paymentOrchestrator) {
        this.orderService = orderService;
        this.paymentOrchestrator = paymentOrchestrator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse createOrder(
            @Valid @RequestBody PaymentRequest request) {

        System.out.println("order creation comple");
//        orderService.createOrder(request);

        return paymentOrchestrator.processPayment(request);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(
            @PathVariable Long orderId) {

        return orderService.getOrder(orderId);
    }
}
