package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.annotations.DataJpaIntegrationTest;
import com.christina.assetmanagement.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <h3>AssetManagement</h3>
 *
 * @category Christina
 * @description <p></p>
 * @date 2022-07-02 20:17
 **/

@DataJpaIntegrationTest
@DisplayName("AuthorRepository should")
class CategoryRepositoryTest {

    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryRepositoryTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("find saved categorys")
    void findSavedCategorys() {
        // given
        Category category1 = createAndSaveCategory("furniture", "ttt");
        Category category2 = createAndSaveCategory("plant", "ttt");

        // when
        List<Category> result = categoryRepository.findAll();

        // then
        assertThat(result).containsExactlyInAnyOrder(category1, category2);
    }

    private Category createAndSaveCategory(String name, String description) {

        Category category = new Category(name, description, false, null);
        categoryRepository.save(category);
        return category;
    }

}