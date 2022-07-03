package com.christina.assetmanagement.service.impl;

import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.repository.CategoryRepository;
import com.christina.assetmanagement.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


/**
 * <h3>AssetManagement</h3>
 *
 * @category Christina
 * @description <p></p>
 * @date 2022-07-03 01:06
 **/

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryService serviceUnderTest;
    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void canSave() {
        // given
        Category expected = new Category("furniture", "ttt", false, null);
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        System.out.println(" expected : " + expected);

        // when
        serviceUnderTest.addCategory(expected);

        // then
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        System.out.println(" categoryArgumentCaptor.getValue(): " + categoryArgumentCaptor.getValue());

        assertThat(categoryArgumentCaptor.getValue()).isEqualTo(expected);
    }

    @Test
    void save_throwsNullPointerException_ifCategoryToSaveIsNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> serviceUnderTest.addCategory(null));
        verify((categoryRepository), never()).save(any(Category.class));
    }

    @Test
    void findById_throwsNullPointerException_ifIdIsNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> serviceUnderTest.getCategoryById(null));
        verify((categoryRepository), never()).findById(anyLong());
    }

    @Test
    void canFindByNonNullId() {
        serviceUnderTest.getCategoryById(1L);
        verify(categoryRepository).findById(anyLong());
    }

    @Test
    void canFindAll() {
        serviceUnderTest.getAllCategories();
        verify(categoryRepository).findAll();
    }

}