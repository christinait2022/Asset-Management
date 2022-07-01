package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.repository.CategoryRepository;
import javax.validation.Valid;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    private CategoryRepository categoryRepository;

    /**
     * list all categories
     *
     * @return {@code List<Category>}
     */
    @GetMapping("/allCategory")
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
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
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category not found for this id :: " + categoryId));
        return ResponseEntity.ok().body(category);
    }

    /**
     * create an new category
     *
     * @param category
     * @return {@code Category}
     */
    @PostMapping("/category")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
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
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category not found for this id :: " + categoryId));

        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());

        final Category updatedCategory = categoryRepository.save(categoryDetails);

        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * delete an category by id
     *
     * @param categoryId
     * @return {@code Map<String, Boolean>}
     * @throws Exception
     */
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