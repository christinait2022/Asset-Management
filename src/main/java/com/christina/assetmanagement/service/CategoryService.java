package com.christina.assetmanagement.service;

import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>AssetManagement</h3>
 *
 * @author panziye
 * @description <p></p>
 * @date 2022-07-02 15:19
 **/

public interface CategoryService {

    ResponseEntity<List<Category>> getAllCategories();

    ResponseEntity<Category> getCategoryById(Long id);

    ResponseEntity<Category> addCategory(Category category);

    ResponseEntity<Category> updateCategoryById(Long id, Category newCategory);

    ResponseEntity<ApiResponse> deleteCategoryById(Long id);
}
