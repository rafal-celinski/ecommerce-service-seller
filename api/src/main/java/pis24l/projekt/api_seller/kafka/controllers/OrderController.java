package pis24l.projekt.api_seller.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.kafka.model.OrderResponse;
import pis24l.projekt.api_seller.kafka.model.ProductOrder;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.services.ProductUpdateService;
import pis24l.projekt.api_seller.services.ProductDeleteService;

@Service
public class OrderController {

    private final ProductUpdateService productUpdateService;
    private final KafkaTemplate<String, OrderResponse> kafkaTemplate;
    private final ProductDeleteService productDeleteService;

    @Autowired
    public OrderController(ProductUpdateService productUpdateService, KafkaTemplate<String, OrderResponse> kafkaTemplate, ProductDeleteService productDeleteService) {
        this.productUpdateService = productUpdateService;
        this.kafkaTemplate = kafkaTemplate;

        this.productDeleteService = productDeleteService;
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

    private void updateProductStatus(String productId) throws Exception {
        productUpdateService.updateProductStatus(productId, ProductStatus.SOLD);
        productDeleteService.deleteProductFromElastic(productId);
    }

    private void sendOrderResponse(String productId, String status, String message) {
        OrderResponse orderResponse = new OrderResponse(productId, status, message);
        kafkaTemplate.send("order_responses", orderResponse);
    }
}

