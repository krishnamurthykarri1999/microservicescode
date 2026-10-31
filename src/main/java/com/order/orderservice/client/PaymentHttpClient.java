package com.order.orderservice.client;
import com.order.orderservice.dto.PaymentRequest;
import com.order.orderservice.dto.PaymentResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentHttpClient {

    private final RestClient restClient;

    public PaymentHttpClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://payment-service")
                .build();
    }

    public PaymentResponse makePayment(PaymentRequest request) {

        System.out.println("Calling Payment Service...");

        return restClient.post()
                .uri("/api/payments")
                .body(request)
                .retrieve()
                .body(PaymentResponse.class);
    }
}
