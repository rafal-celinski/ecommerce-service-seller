package pis24l.projekt.api_client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_client.model.Category;
import pis24l.projekt.api_client.repositories.CategoryRepository;
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