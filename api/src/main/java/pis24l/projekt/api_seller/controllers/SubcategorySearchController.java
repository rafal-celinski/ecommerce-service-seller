package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api_seller.model.Subcategory;
import pis24l.projekt.api_seller.service.SubcategorySearchService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/subcategories")
public class SubcategorySearchController {

    private final SubcategorySearchService subcategoryService;

    @Autowired
    public SubcategorySearchController(SubcategorySearchService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    public List<Subcategory> getAllSubcategories() {
        return subcategoryService.getAllSubcategories();
    }

    @GetMapping("/category")
    public List<Subcategory> getSubcategoriesByCategoryId(@RequestParam String categoryId) {
        return subcategoryService.getSubcategoriesByCategoryId(categoryId);
    }

}
