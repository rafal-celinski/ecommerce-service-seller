package pis24l.projekt.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pis24l.projekt.api.model.Category;
import pis24l.projekt.api.service.CategorySearchService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorySearchController {

    private final CategorySearchService categorySearchService;
    @Autowired
    public CategorySearchController(CategorySearchService categorySearchService) {
        this.categorySearchService = categorySearchService;
    }


    @GetMapping
    public List<Category> getAllCategories() {
        return categorySearchService.getAllCategories();
    }

}