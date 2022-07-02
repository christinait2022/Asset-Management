package com.christina.assetmanagement.controller;


import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.payload.ApiResponse;
import com.christina.assetmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * CategoryController
 *
 * @author Christina
 * @date 2022/07/01
 */
@RestController
@RequestMapping("")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * list all categories
     *
     * @return {@code List<Category>}
     */
    @GetMapping("/allCategory")
    public ResponseEntity<List<Category>> getAllCategory() {

        return categoryService.getAllCategories();
    }


    /**
     * get an category by id
     *
     * @param categoryId
     * @return {@code ResponseEntity<Category>}
     * @throws Exception
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId) throws Exception {

        return categoryService.getCategoryById(categoryId);
    }

    /**
     * create an new category
     *
     * @param category
     * @return {@code Category}
     */
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    /**
     * update an category by id
     *
     * @param categoryId
     * @param categoryDetails
     * @return {@code ResponseEntity<Category>}
     * @throws Exception
     */
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
                                                   @Valid @RequestBody Category categoryDetails) throws Exception {
        return categoryService.updateCategoryById(categoryId, categoryDetails);
    }

    /**
     * delete an category by id
     *
     * @param categoryId
     * @return {@code Map<String, Boolean>}
     * @throws Exception
     */
    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(value = "id") Long categoryId) {
        return categoryService.deleteCategoryById(categoryId);
    }

}