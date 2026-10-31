package com.order.orderservice.service;




import com.order.orderservice.dto.CreateOrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.dto.ProductResponse;
import com.order.orderservice.entity.OrderEntity;
import com.order.orderservice.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(
            OrderRepository orderRepository,
            ProductClient productClient) {

        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {

        // 1. Get product details from Product Service
        ProductResponse product =
                productClient.getProduct(request.productId());

        // 2. Create the order using the returned product details
        OrderEntity order = new OrderEntity(
                product.id(),
                product.name(),
                product.price(),
                request.quantity()
        );

        // 3. Save the order in the Order Service database
        OrderEntity savedOrder = orderRepository.save(order);

        // 4. Return the response
        return toResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long orderId) {

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found: " + orderId
                ));

        return toResponse(order);
    }

    private OrderResponse toResponse(OrderEntity order) {

        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getProductName(),
                order.getUnitPrice(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
