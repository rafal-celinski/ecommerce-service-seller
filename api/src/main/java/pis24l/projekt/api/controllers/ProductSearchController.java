package pis24l.projekt.api.controllers;

import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pis24l.projekt.api.dtos.ProductDTO;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductSearchController {
    private final ProductRepository productRepository;
    private  final ModelMapper modelMapper;

    public ProductSearchController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProducts(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long subcategory,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String location,
            @RequestParam(required = false,defaultValue = "false") Boolean isTesting) {
        // Assuming search is for the product title
        if (!isTesting){
            return mapProductsToDTOs(productRepository.findAll());
        }
        else {
            return mapProductsToDTOs(productRepository.findByPriceBetweenAndTitleContainingAndCategoryAndSubcategoryAndLocation(
                    minPrice, maxPrice, search, category, subcategory, location));
        }
    }
    private List<ProductDTO> mapProductsToDTOs(List<Product> products) {
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
