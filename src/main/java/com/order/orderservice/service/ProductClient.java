package com.order.orderservice.service;


import com.order.orderservice.dto.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProductClient {

    private final RestClient restClient;

    public ProductClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://product-service")
                .build();
    }

    public ProductResponse getProduct(Long productId) {

        try {
            ProductResponse product = restClient
                    .get()
                    .uri("/api/products/{id}", productId)
                    .retrieve()
                    .body(ProductResponse.class);

            if (product == null || product.id() == null
                    || product.name() == null
                    || product.price() == null
                    || product.price().signum() < 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "Product Service returned invalid product data"
                );
            }

            return product;

        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found: " + productId
            );

        } catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Product Service rejected the request"
            );

        } catch (RestClientException ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Product Service is currently unavailable"
            );
        }
    }
}