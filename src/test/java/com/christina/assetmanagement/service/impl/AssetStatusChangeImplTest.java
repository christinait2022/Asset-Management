package com.christina.assetmanagement.service.impl;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.model.Employee;
import com.christina.assetmanagement.model.Status;
import com.christina.assetmanagement.repository.AssetRepository;
import com.christina.assetmanagement.repository.AssetStatusChangeRepository;
import com.christina.assetmanagement.repository.EmployeeRepository;
import com.christina.assetmanagement.service.AssetStatusChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-03 21:11
 **/
@ExtendWith(MockitoExtension.class)
class AssetStatusChangeImplTest {

    private AssetStatusChangeServiceImpl serviceUnderTest;

    @Mock
    private AssetRepository assetRepository;
    @Mock
    private AssetStatusChangeRepository assetStatusChangeRepository;
    @Mock
    private EmployeeRepository employeeRepository;


    @BeforeEach
    void setUp() {
        serviceUnderTest = new AssetStatusChangeServiceImpl(assetRepository, assetStatusChangeRepository, employeeRepository);
    }

    @Test
    void getAllAssetStatusChange() {
        serviceUnderTest.getAllAssetStatusChange();
        verify(assetStatusChangeRepository).findAll();
    }

    @Test
    void lendAsset_incomplete_whenAssetIsAssigned() {
        Category expected = new Category("furniture", "ttt", false, null);
        Asset asset = new Asset("test",null ,false,null, expected, Status.Assigned);
        Employee employee = new Employee(null,null);

        serviceUnderTest.lendAsset(1,1,null);


        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        System.out.println(" expected : " + expected);
    }

    @Test
    void returnAsset() {
    }

    @Test
    void checkAsset() {
    }
}