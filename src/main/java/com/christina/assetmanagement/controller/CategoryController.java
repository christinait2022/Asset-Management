package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/allCategory")
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category not found for this id :: " + categoryId));
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/category")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
                                                   @Valid @RequestBody Category categoryDetails) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category not found for this id :: " + categoryId));

        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());

        final Category updatedCategory = categoryRepository.save(categoryDetails);

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/category/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long categoryId)
            throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category not found for this id :: " + categoryId));

        categoryRepository.delete(category);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}