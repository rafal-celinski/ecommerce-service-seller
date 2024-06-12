package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api_seller.services.ProductDeleteService;


@RestController
@RequestMapping("/products")
public class ProductDeleteController {

    private final ProductDeleteService productDeleteService;

    @Autowired
    public ProductDeleteController(ProductDeleteService productDeleteService) {
        this.productDeleteService = productDeleteService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        return productDeleteService.deleteProduct(id);
    }

}
