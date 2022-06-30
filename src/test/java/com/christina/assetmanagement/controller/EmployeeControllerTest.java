package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.AssetStatusChange;
import com.christina.assetmanagement.model.Status;
import com.christina.assetmanagement.repository.AssetRepository;
import com.christina.assetmanagement.repository.AssetStatusChangeRepository;
import com.christina.assetmanagement.repository.CategoryRepository;
import com.christina.assetmanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private AssetStatusChangeRepository assetStatusChangeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void lendAsset() {
        int asset_id = 1;
        int employee_id = 1;
        AssetStatusChange assetStatusChange = restTemplate.getForObject(getRootUrl() +"/employee/"+ employee_id
                +"/lendAsset/"+asset_id, AssetStatusChange.class);
        assertNotNull(assetStatusChange);
    }

    @Test
    void returnAsset() {
        int asset_id = 1;
        int employee_id = 1;
        AssetStatusChange assetStatusChange = restTemplate.getForObject(getRootUrl() +"/employee/"+ employee_id
                +"/returnAsset/"+asset_id, AssetStatusChange.class);
        assertNotNull(assetStatusChange);
    }

    @Test
    void checkAsset() {
        int asset_id = 1;
        int employee_id = 1;
        AssetStatusChange assetStatusChange = restTemplate.getForObject(getRootUrl() +"/employee/"+ employee_id
                +"/checkAsset/"+asset_id, AssetStatusChange.class);
        assertNotNull(assetStatusChange);
    }
}