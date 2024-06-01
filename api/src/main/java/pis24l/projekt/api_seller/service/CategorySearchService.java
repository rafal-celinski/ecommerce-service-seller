package pis24l.projekt.api_seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.model.Category;
import pis24l.projekt.api_seller.repositories.CategoryRepository;
import java.util.List;

@Service
public class CategorySearchService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySearchService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}