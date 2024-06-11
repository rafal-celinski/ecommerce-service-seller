package pis24l.projekt.api_seller.kafka.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.kafka.model.ProductOrder;
import pis24l.projekt.api_seller.service.ProductUpdateService;

@Service
public class OrderListener {

    private final ProductUpdateService productUpdateService;

    @Autowired
    public OrderListener(ProductUpdateService productUpdateService) {
        this.productUpdateService = productUpdateService;
    }

    @KafkaListener(topics = "product_orders", groupId = "group_id")
    public void listen(ProductOrder productOrder) {
        System.out.println("Received product order: " + productOrder);
        updateProductStatus(productOrder.getProductId());
    }

    private void updateProductStatus(String productId) {
        productUpdateService.updateProductStatus(productId, "bought");
    }
}

