package pis24l.projekt.api_seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.model.Subcategory;
import pis24l.projekt.api_seller.repositories.SubcategoryRepository;

import java.util.List;

@Service
public class SubcategorySearchService {

    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategorySearchService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }


    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    public List<Subcategory> getSubcategoriesByCategoryId(Long categoryId) {
        return subcategoryRepository.findSubcategoriesByCategoryId(categoryId);
    }
}
