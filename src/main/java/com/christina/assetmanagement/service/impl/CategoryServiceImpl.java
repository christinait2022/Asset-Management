package com.christina.assetmanagement.service.impl;

import com.christina.assetmanagement.exception.ResourceNotFoundException;
import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.payload.ApiResponse;
import com.christina.assetmanagement.repository.CategoryRepository;
import com.christina.assetmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-02 15:25
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return new ResponseEntity<>(category, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Category> addCategory(Category category) {
        if (category == null) {
            throw new NullPointerException("category is null");
        }
        Category newCategory = categoryRepository.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Category> updateCategoryById(Long id, Category categoryDetails) {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        if (categoryDetails == null) {
            throw new NullPointerException("category is null");
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse> deleteCategoryById(Long id) {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "You successfully deleted category"), HttpStatus.OK);
    }
}
