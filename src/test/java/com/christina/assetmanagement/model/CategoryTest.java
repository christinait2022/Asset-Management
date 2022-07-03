package com.christina.assetmanagement.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-02 19:37
 **/
@DisplayName("Category should")
class CategoryTest {


    @Test
    @DisplayName("throw a Null Pointer Exception on an attempt to create with a null name")
    void notAcceptNullName() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                new Category(null, null, false, null)
        );
    }

    @Test
    void findBookByTitle() {
        // given


    }

}