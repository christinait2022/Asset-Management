package com.christina.assetmanagement.service.impl;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-03 19:44
 **/
@ExtendWith(MockitoExtension.class)
class AssetServiceImplTest {

    private AssetServiceImpl serviceUnderTest;
    @Mock
    private AssetRepository assetRepository;


    @BeforeEach
    void setUp() {
        serviceUnderTest = new AssetServiceImpl(assetRepository);
    }

    @Test
    void save_throwsNullPointerException_ifAssetIsNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> serviceUnderTest.addAsset(null));
        verify(assetRepository, never()).save(any());
    }

    @Test
    void canFindByNonNullId() {
        // given
        Long expected = 1L;
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        // when
        serviceUnderTest.getAssetById(expected);

        // then
        verify(assetRepository).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(expected);
    }

    @Test
    void canFindAll() {
        serviceUnderTest.getAllAsset();
        verify(assetRepository).findAll();
    }

    @Test
    void getByNameContaining_throwsNullPointerException_ifNameIsNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> serviceUnderTest.getByNameContaining(null));
        verify(assetRepository, never()).findByNameContaining(anyString());
    }

    @Test
    void canGetByNameContainingIfStringNotNull() {
        // given
        String expected = "test";
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // when
        serviceUnderTest.getByNameContaining(expected);

        // then
        verify(assetRepository).findByNameContaining(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue()).isEqualTo(expected);
    }

    @Test
    void deleteAsset_throwsNullPointerException_ifIdIsNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> serviceUnderTest.deleteAssetById(null));
        verify(assetRepository, never()).delete(any(Asset.class));
    }

    @Test
    void canDeleteAssetIfIdIsNotNull() {
        // given
        Long expected = 1L;
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        // when
        serviceUnderTest.deleteAssetById(expected);

        // then
        verify(assetRepository).deleteById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(expected);
    }

}