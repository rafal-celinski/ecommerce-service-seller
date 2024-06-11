package pis24l.projekt.api_seller.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.kafka.model.OrderResponse;
import pis24l.projekt.api_seller.kafka.model.ProductOrder;
import pis24l.projekt.api_seller.service.ProductUpdateService;

@Service
public class OrderController {

    private final ProductUpdateService productUpdateService;
    private final KafkaTemplate<String, OrderResponse> kafkaTemplate;

    @Autowired
    public OrderController(ProductUpdateService productUpdateService, KafkaTemplate<String, OrderResponse> kafkaTemplate) {
        this.productUpdateService = productUpdateService;
        this.kafkaTemplate = kafkaTemplate;

    }

    @KafkaListener(topics = "product_orders", groupId = "group_id")
    public void listen(ProductOrder productOrder) {
        System.out.println("Received product order: " + productOrder);
        try {
            updateProductStatus(productOrder.getProductId());
            sendOrderResponse(productOrder.getProductId(), "SUCCESS", "Order processed successfully.");
        } catch (Exception e) {
            sendOrderResponse(productOrder.getProductId(), "ERROR", "Order processing failed: " + e.getMessage());
        }
    }

    private void updateProductStatus(String productId) {
        productUpdateService.updateProductStatus(productId, "Bought");
    }

    private void sendOrderResponse(String productId, String status, String message) {
        OrderResponse orderResponse = new OrderResponse(productId, status, message);
        kafkaTemplate.send("order_responses", orderResponse);
    }
}

