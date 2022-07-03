package com.christina.assetmanagement.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-02 19:36
 **/
@DisplayName("Asset should")
class AssetTest {
    @Test
    @DisplayName("throw a Null Pointer Exception on an attempt to create with a null name")
    void notAcceptNullName() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                new Asset(null, null, false, null, null, null)
        );
    }


}